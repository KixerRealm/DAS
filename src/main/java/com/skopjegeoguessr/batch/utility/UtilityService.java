package com.skopjegeoguessr.batch.utility;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.model.PlacesSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class UtilityService {

	@Value("${gcp-key}")
	public String gcpApiKey;

	public PlacesSearchResponse wrapWithContext(ContextMethod methods) throws IOException, InterruptedException, ApiException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(gcpApiKey).build();
		PlacesSearchResponse response;
		try {
			response = methods.execute(context);
		} catch (InvalidRequestException ex) {
			Thread.sleep(3000);
			response = methods.execute(context);
		}
		context.shutdown();
		return response;
	}


	public JobParameters buildJobParameters(String query) {
		return buildJobParameters(query, "");
	}

	public JobParameters buildJobParameters(String query, String nextPageToken) {
		return new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.addString("query", query)
				.addString("nextPageToken", nextPageToken)
				.addString("runId", RandomStringUtils.randomAlphanumeric(12))
				.toJobParameters();
	}

	public void createClassPathFile(String fileName) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(Objects.requireNonNull(classLoader.getResource(".")).getFile() + "/" + fileName);
			if (!file.createNewFile()) {
				log.warn("File already exists.");
			}
		} catch (IOException e) {
			log.error("Could not create file...");
		}
	}
}