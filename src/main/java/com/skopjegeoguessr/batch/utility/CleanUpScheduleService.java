package com.skopjegeoguessr.batch.utility;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class CleanUpScheduleService {

	@Value("classpath:/temp/*")
	private Resource[] resources;

	private final JobExplorer jobExplorer;

	@Async
	@Scheduled(fixedRate = 10, timeUnit = TimeUnit.MINUTES)
	public void cleanTempFolder() throws IOException {
		Set<JobExecution> data = jobExplorer.findRunningJobExecutions("importJob");
		if(!data.isEmpty()) {
			return;
		}

		for(Resource resource: resources) {
			log.info("Trying to delete file: {}", resource.getFilename());
			boolean success = Files.deleteIfExists(resource.getFile().toPath());
			if(!success) {
				log.error("Failed deleting file: {}", resource.getFilename());
			}
		}
	}
}
