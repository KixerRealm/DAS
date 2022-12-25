package com.skopjegeoguessr.batch.jobs.steps;

import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.batch.jobs.processors.PlacesPageProcessor;
import com.skopjegeoguessr.batch.jobs.steps.readers.PlacesPageReader;
import com.skopjegeoguessr.batch.jobs.steps.writers.PlacesPageItemWriter;
import com.skopjegeoguessr.batch.jobs.steps.writers.builders.PlacesPageItemWriterBuilder;
import com.skopjegeoguessr.batch.places.PlaceProcessorParameters;
import com.skopjegeoguessr.batch.utility.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class PlacesPageStepConfig {

	private final StepBuilderFactory stepBuilderFactory;
	private final PlacesPageProcessor placesPageProcessor;
	private final PlacesPageReader placesPageReader;
	private final PlacesPageItemWriter placesPageWriter;
	private final UtilityService utilityService;

	@Bean
	public Step placesPageStep() {
		return stepBuilderFactory.get("placesPageStep")
				.<PlaceProcessorParameters, PlacesSearchResult[]>chunk(10)
				.reader(placesPageReader)
				.processor(placesPageProcessor)
				.writer(placesPageWriter)
				.build();
	}

	@Bean
	@StepScope
	public PlacesPageReader placesPageReader(
			@Value("#{jobParameters['query']}") String query,
			@Value("#{jobParameters['nextPageToken']}") String nextPageToken
	) {
		return new PlacesPageReader(query, nextPageToken);
	}

	@Bean
	@StepScope
	public PlacesPageItemWriter placesPageWriter(
			@Value("#{jobParameters['runId']}") String id
	) {
		final String fileName = "/temp/places-page-" + id + ".json";
		return new PlacesPageItemWriterBuilder()
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
				.resource(new FileSystemResource(fileName))
				.name("placesPageWriter")
				.build();
	}

}
