package com.skopjegeoguessr.batch.places;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class PlaceController {

	private final JobLauncher jobLauncher;

	private final Job importJob;

	@PostMapping("/coffee")
	public void coffeeJobStart() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.addString("query", "coffeeshops+skopje+macedonia")
				.addString("result-location", "src/main/resources/static/results.json")
				.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(importJob, jobParameters);
		log.info("Job Execution: " + jobExecution.getStatus());
	}

	@PostMapping("/landmark")
	public void landmarkJobStart() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.addString("query", "landmarks+skopje+macedonia")
				.addString("result-location", "src/main/resources/static/results1.json")
				.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(importJob, jobParameters);
		log.info("Job Execution: " + jobExecution.getStatus());
	}
}
