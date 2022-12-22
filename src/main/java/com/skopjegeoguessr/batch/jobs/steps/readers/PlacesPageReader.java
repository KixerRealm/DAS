package com.skopjegeoguessr.batch.jobs.steps.readers;

import com.skopjegeoguessr.batch.places.PlaceProcessorParameters;
import org.springframework.batch.item.ItemReader;


public class PlacesPageReader implements ItemReader<PlaceProcessorParameters> {

	private final PlaceProcessorParameters placeProcessorParameters;
	private boolean fetched = false;

	public PlacesPageReader(String query, String nextPageToken) {
		this.placeProcessorParameters = new PlaceProcessorParameters(query, nextPageToken);
	}

	@Override
	public PlaceProcessorParameters read() {
		if(fetched) {
			return null;
		}
		this.fetched = true;
		return placeProcessorParameters;
	}
}
