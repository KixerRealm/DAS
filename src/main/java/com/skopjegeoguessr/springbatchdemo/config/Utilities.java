package com.skopjegeoguessr.springbatchdemo.config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utilities {

    public static final String GCP_API_KEY = "AIzaSyCZMPjDh82Z_NKVyeTHsddOYS_hMAmQg8w";

    public static PlacesSearchResponse wrapWithContext(ContextedMethods methods) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(GCP_API_KEY).build();
        PlacesSearchResponse response = methods.execute(context);
        context.shutdown();
        return response;
    }
}