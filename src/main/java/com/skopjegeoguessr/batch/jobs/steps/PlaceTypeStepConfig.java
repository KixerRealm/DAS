package com.skopjegeoguessr.batch.jobs.steps;

import com.skopjegeoguessr.batch.jobs.processors.PlaceTypeProcessor;
import com.skopjegeoguessr.batch.places.model.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
@RequiredArgsConstructor
public class PlaceTypeStepConfig {

	private final StepBuilderFactory stepBuilderFactory;
	private final PlaceTypeProcessor placeTypeProcessor;
	private final JsonItemReader<Place> placesTypeReader;
	private final RepositoryItemWriter<Place> placeRepositoryWriter;

	@Bean
	public Step placesTypeStep() {
		return stepBuilderFactory.get("placesTypeStep")
				.<Place, Place>chunk(10)
				.reader(placesTypeReader)
				.processor(placeTypeProcessor)
				.writer(placeRepositoryWriter)
				.build();
	}

	@Bean
	@StepScope
	public JsonItemReader<Place> placesTypeReader(
			@Value("#{jobParameters['runId']}") String id
	) {
		return new JsonItemReaderBuilder<Place>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(Place.class))
				.resource(new FileSystemResource("/temp/places-photo-" + id + ".json"))
				.name("placesTypeReader")
				.build();
	}

}
