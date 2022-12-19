package com.skopjegeoguessr.batch.jobs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.batch.config.processors.PlaceProcessor;
import com.skopjegeoguessr.batch.places.PlaceMapper;
import com.skopjegeoguessr.batch.places.model.Place;
import com.skopjegeoguessr.batch.utility.UtilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CoffeeJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final UtilityService utilityService;

	private final Gson gson;
	private final PlaceProcessor processor;
	private final RepositoryItemWriter<Place> writer;

	@Lazy
	@Autowired
	@Qualifier("CoffeeReader")
	private JsonItemReader<Place> reader;

	@Lazy
	@Autowired
	@Qualifier("CoffeeStep")
	private Step coffeeStep;

	@Bean
	public Job coffeeJob() {
		return jobBuilderFactory.get("coffeeJob").start(coffeeStep).build();
	}

	@Bean(name = "CoffeeStep")
	public Step coffeeStep() {
		return stepBuilderFactory.get("CoffeeStep")
				.<Place, Place>chunk(10)
				.reader(reader).processor(processor).writer(writer)
				.build();
	}

	@Bean(name = "CoffeeReader")
	public JsonItemReader<Place> coffeeItemReader() throws IOException, InterruptedException, ApiException {
		final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final JacksonJsonObjectReader<Place> jsonObjectReader = new JacksonJsonObjectReader<>(Place.class);
		jsonObjectReader.setMapper(mapper);
		PlacesSearchResponse results = utilityService.wrapWithContext(context ->
				PlacesApi.textSearchQuery(
						context,
						"coffeeshops+skopje+macedonia"
				).await());

		List<PlacesSearchResponse> responses = new java.util.ArrayList<>(List.of(results));
		Thread.sleep(3000);
		while (responses.get(responses.size() - 1).nextPageToken != null &&
				responses.get(responses.size() - 1).nextPageToken.length() != 0) {
			Thread.sleep(3000);
			responses.add(
					utilityService.wrapWithContext(context ->
							PlacesApi.textSearchQuery(
									context,
									"coffeeshops+skopje+macedonia"
							).pageToken(responses.get(responses.size() - 1).nextPageToken).await()
					)
			);
		}
		List<PlacesSearchResult[]> responses1 = new ArrayList<>();
		for (PlacesSearchResponse p : responses) {
			responses1.add(p.results);
		}
		List<PlacesSearchResult> responses2 = new ArrayList<>();
		for (PlacesSearchResult[] p : responses1) {
			responses2.addAll(Arrays.asList(p));
		}

		List<Place> placeList = responses2.stream().map(PlaceMapper.INSTANCE::toEntity).toList();

		FileWriter writer = new FileWriter("src/main/resources/static/results.json");
		gson.toJson(placeList, writer);
		writer.flush();
		return new JsonItemReaderBuilder<Place>().jsonObjectReader(jsonObjectReader)
				.resource(new FileSystemResource("src/main/resources/static/results.json"))
				.name("jsonItemReader")
				.build();
	}
}
