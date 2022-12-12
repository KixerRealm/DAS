package com.skopjegeoguessr.springbatchdemo.config.processors;

import com.skopjegeoguessr.springbatchdemo.model.Place;

import com.skopjegeoguessr.springbatchdemo.model.enums.PlaceType;
import org.springframework.batch.item.ItemProcessor;

public class PlaceProcessor implements ItemProcessor<Place, Place> {

    @Override
    public Place process(Place place) throws Exception {
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
