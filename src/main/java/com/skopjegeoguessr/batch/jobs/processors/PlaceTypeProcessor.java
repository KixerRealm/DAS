package com.skopjegeoguessr.batch.jobs.processors;

import com.skopjegeoguessr.batch.places.model.Place;
import com.skopjegeoguessr.batch.places.model.PlaceType;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class PlaceTypeProcessor implements ItemProcessor<Place, Place> {

	@Override
	public Place process(Place place) {
		if (place.getTypes().contains("cafe") || (place.getTypes().contains("food") && place.getTypes().contains("point_of_interest"))) {
			place.setPlaceType(PlaceType.COFFEE);
		} else {
			place.setPlaceType(PlaceType.LANDMARKS);
		}

		return place;
	}
}
