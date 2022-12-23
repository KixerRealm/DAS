package com.skopjegeoguessr.batch.jobs.processors;

import com.skopjegeoguessr.batch.places.model.Place;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
public class PlacePhotoFilter implements ItemProcessor<Place, Place> {

	@Override
	public Place process(Place place) {
		if(place.getPhotoReference() == null) {
			return null;
		}

		return place;
	}
}
