package com.skopjegeoguessr.batch.jobs;

import com.skopjegeoguessr.batch.jobs.processors.PlaceTypeProcessor;
import com.skopjegeoguessr.batch.places.model.Place;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ImportJobConfig {

	private final JobBuilderFactory jobBuilderFactory;


	private final Step placesPageStep;
	private final Step placesMappingStep;
	private final Step placesPhotoStep;
	private final Step placesTypeStep;

	@Bean
	public Job importJob() {
		return jobBuilderFactory.get("importJob")
				.incrementer(new RunIdIncrementer())
				.start(placesPageStep)
				.next(placesMappingStep)
				.next(placesPhotoStep)
				.next(placesTypeStep)
				.build();
	}

}

