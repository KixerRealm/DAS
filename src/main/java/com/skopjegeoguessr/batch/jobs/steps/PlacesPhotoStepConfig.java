package com.skopjegeoguessr.batch.jobs.steps;

import com.skopjegeoguessr.batch.jobs.processors.PlacePhotoFilter;
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
import org.springframework.core.io.FileSystemResource;

@Configuration
@RequiredArgsConstructor
public class PlacesPhotoStepConfig {

	private final StepBuilderFactory stepBuilderFactory;
	private final PlacePhotoFilter placePhotoFilter;
	private final JsonItemReader<Place> placesPhotoReader;
	private final JsonFileItemWriter<Place> placesPhotoWriter;
	private final UtilityService utilityService;

	@Bean
	public Step placesPhotoStep() {
		return stepBuilderFactory.get("placesPhotoStep")
				.<Place, Place>chunk(10)
				.reader(placesPhotoReader)
				.processor(placePhotoFilter)
				.writer(placesPhotoWriter)
				.build();
	}

	@Bean
	@StepScope
	public JsonItemReader<Place> placesPhotoReader(
			@Value("#{jobParameters['runId']}") String id
	) {
		return new JsonItemReaderBuilder<Place>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(Place.class))
				.resource(new FileSystemResource("/temp/places-mapping-" + id + ".json"))
				.name("placesPhotoReader")
				.build();
	}

	@Bean
	@StepScope
	public JsonFileItemWriter<Place> placesPhotoWriter(
			@Value("#{jobParameters['runId']}") String id
	) {
		final String fileName = "/temp/places-photo-" + id + ".json";
		return new JsonFileItemWriterBuilder<Place>()
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
				.resource(new FileSystemResource(fileName))
				.name("placesPhotoWriter")
				.build();
	}

}
