package com.skopjegeoguessr.batch.jobs.processors;

import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.batch.places.PlaceProcessorParameters;
import com.skopjegeoguessr.batch.utility.UtilityService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Component
public class PlacesPageProcessor implements ItemProcessor<PlaceProcessorParameters, PlacesSearchResult[]> {

	private final Job importJob;
	private final JobLauncher asyncJobLauncher;
	private final UtilityService utilityService;

	@Lazy
	public PlacesPageProcessor(Job importJob, JobLauncher asyncJobLauncher, UtilityService utilityService) {
		this.importJob = importJob;
		this.asyncJobLauncher = asyncJobLauncher;
		this.utilityService = utilityService;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PlacesSearchResult[] process(@NonNull PlaceProcessorParameters parameters) throws JobInstanceAlreadyCompleteException,
			JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, IOException,
			InterruptedException, ApiException {

		PlacesSearchResponse result = utilityService.wrapWithContext(context -> StringUtils.isBlank(parameters.getNextPageToken()) ?
				PlacesApi.textSearchQuery(context, parameters.getQuery()).await() :
				PlacesApi.textSearchQuery(context, parameters.getQuery())
						.pageToken(parameters.getNextPageToken())
						.await()
		);

		if (StringUtils.isNotBlank(result.nextPageToken)) {
			asyncJobLauncher.run(importJob, utilityService.buildJobParameters(parameters.getQuery(), result.nextPageToken));
		}

		return result.results;
	}
}
