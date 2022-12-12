package com.skopjegeoguessr.springbatchdemo.controller;

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

@RestController
@RequestMapping("/jobs")
public class TestController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("jsonCoffeeJob")
    private Job job;

    @Autowired
    @Qualifier("jsonLandmarkJob")
    private Job job1;

    @PostMapping("/jsonJob1")
    public void importJsonToDBJob(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt ",System.currentTimeMillis()).toJobParameters();
        try {
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            JobExecution jobExecution1 = jobLauncher.run(job1, jobParameters);
            System.out.println("Job Execution: " + jobExecution.getStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}
