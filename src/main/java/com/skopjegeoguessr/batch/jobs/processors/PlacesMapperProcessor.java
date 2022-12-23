package com.skopjegeoguessr.batch.jobs.processors;

import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.batch.places.PlaceMapper;
import com.skopjegeoguessr.batch.places.model.Place;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class PlacesMapperProcessor implements ItemProcessor<PlacesSearchResult, Place> {
	@Override
	public Place process(PlacesSearchResult result) {
		return PlaceMapper.INSTANCE.toEntity(result);
	}
}
