package com.skopjegeoguessr.batch.config;

import com.skopjegeoguessr.batch.places.PlaceRepository;
import com.skopjegeoguessr.batch.places.model.Place;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {

	private final JobRepository jobRepository;
	private final PlaceRepository placeRepository;
	private final TaskExecutor threadPoolTaskExecutor;

	@Bean
	public RepositoryItemWriter<Place> placeRepositoryWriter() {
		RepositoryItemWriter<Place> writer = new RepositoryItemWriter<>();
		writer.setRepository(placeRepository);
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public JobLauncher asyncJobLauncher() {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.setTaskExecutor(threadPoolTaskExecutor);
		return jobLauncher;
	}

}
