package com.skopjegeoguessr.batch.jobs.steps;

import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.batch.jobs.processors.PlacesMapperProcessor;
import com.skopjegeoguessr.batch.places.model.Place;
import com.skopjegeoguessr.batch.utility.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class PlacesMappingStepConfig {

	private final StepBuilderFactory stepBuilderFactory;
	private final PlacesMapperProcessor placesMapperProcessor;
	private final JsonItemReader<PlacesSearchResult> placesMappingReader;
	private final JsonFileItemWriter<Place> placesMappingWriter;
	private final UtilityService utilityService;

	@Bean
	public Step placesMappingStep() {
		return stepBuilderFactory.get("placesMappingStep")
				.<PlacesSearchResult, Place>chunk(10)
				.reader(placesMappingReader)
				.processor(placesMapperProcessor)
				.writer(placesMappingWriter)
				.build();
	}

	@Bean
	@StepScope
	public JsonItemReader<PlacesSearchResult> placesMappingReader(
			@Value("#{jobParameters['runId']}") String id
	) {
		return new JsonItemReaderBuilder<PlacesSearchResult>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(PlacesSearchResult.class))
				.resource(new ClassPathResource("temp/places-page-" + id + ".json"))
				.name("placesMappingReader")
				.build();
	}

	@Bean
	@StepScope
	public JsonFileItemWriter<Place> placesMappingWriter(
			@Value("#{jobParameters['runId']}") String id
	) {
		final String fileName = "temp/places-mapping-" + id + ".json";
		utilityService.createClassPathFile(fileName);
		return new JsonFileItemWriterBuilder<Place>()
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
				.resource(new ClassPathResource(fileName))
				.name("placesMappingWriter")
				.build();
	}

}
