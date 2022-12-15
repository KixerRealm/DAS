package com.skopjegeoguessr.batch.utility;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;

import java.io.IOException;

public interface ContextedMethods {

    PlacesSearchResponse execute(GeoApiContext context) throws IOException, InterruptedException, ApiException;

}