package com.skopjegeoguessr.batch.places;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class PlaceController {

	private final JobLauncher jobLauncher;

	@Autowired
	@Qualifier("coffeeJob")
	private Job job;

	@Autowired
	@Qualifier("landmarkJob")
	private Job job1;

	@PostMapping("/coffee")
	public void importJsonToDBJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		log.info("Job Execution: " + jobExecution.getStatus());
	}
}
