package com.skopjegeoguessr.batch.places;

import com.skopjegeoguessr.batch.utility.UtilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class PlaceController {

	private final JobLauncher asyncJobLauncher;
	private final UtilityService utilityService;

	@Value("${sko.queries}")
	private String[] queries;

	private final Job importJob;

	@PostMapping("/coffee")
	public void coffeeJobStart() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		JobParameters jobParameters = utilityService.buildJobParameters("coffeeshops+skopje+macedonia");
		JobExecution jobExecution = asyncJobLauncher.run(importJob, jobParameters);
		log.info("Job Execution: " + jobExecution.getStatus());
	}

	@PostMapping("/landmark")
	public void landmarkJobStart() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		JobParameters jobParameters = utilityService.buildJobParameters("landmarks+skopje+macedonia");
		JobExecution jobExecution = asyncJobLauncher.run(importJob, jobParameters);
		log.info("Job Execution: " + jobExecution.getStatus());
	}

	@PostMapping("/composite")
	public void compositeJobStart() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		for(String query: queries) {
			JobParameters jobParameters = utilityService.buildJobParameters(query);
			JobExecution jobExecution = asyncJobLauncher.run(importJob, jobParameters);
			log.info("Job Execution: " + jobExecution.getStatus());
		}
		log.info("Completed composite jobs.");
	}
}
