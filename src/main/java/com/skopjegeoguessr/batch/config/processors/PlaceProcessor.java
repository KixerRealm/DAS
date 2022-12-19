package com.skopjegeoguessr.batch.config.processors;

import com.skopjegeoguessr.batch.model.Place;

import com.skopjegeoguessr.batch.model.PlaceType;
import org.springframework.batch.item.ItemProcessor;

public class PlaceProcessor implements ItemProcessor<Place, Place> {

    @Override
    public Place process(Place place) {
        if(place.getTypes().contains("cafe")||(place.getTypes().contains("food")&& place.getTypes().contains("point_of_interest"))){
            place.setPlaceType(PlaceType.COFFEE_SHOP);
        }else{
            place.setPlaceType(PlaceType.LANDMARK);
        }

        if(place.getPhotoReference()!=null){
            return place;
        }else{
            return null;
        }
    }
}
