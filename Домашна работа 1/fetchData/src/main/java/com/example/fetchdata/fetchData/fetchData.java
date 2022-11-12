package com.example.fetchdata.fetchData;

import com.google.gson.*;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Component
public class fetchData {

    @PostConstruct
    public void fetchData() throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCZMPjDh82Z_NKVyeTHsddOYS_hMAmQg8w")
                .build();
        PlacesSearchResponse results =  PlacesApi.textSearchQuery(context,
                "coffeeshops+skopje+macedonia").await();
        Thread.sleep(2000);
        PlacesSearchResponse results1 =  PlacesApi.textSearchQuery(context,
                "coffeeshops+skopje+macedonia").pageToken(results.nextPageToken).await();
        Thread.sleep(2000);
        PlacesSearchResponse results2 =  PlacesApi.textSearchQuery(context,
                "coffeeshops+skopje+macedonia").pageToken(results1.nextPageToken).await();

        PlacesSearchResponse results3 =  PlacesApi.textSearchQuery(context,
                "landmark+skopje+macedonia").await();

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, type, jsonDeserializationContext) -> {
            try{
                return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException e){
                return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
            }
        }).setPrettyPrinting().create();

        System.out.println(gson.toJson(results));
        System.out.println(gson.toJson(results1));
        System.out.println(gson.toJson(results2));
        System.out.println(gson.toJson(results3));

        // Invoke .shutdown() after your application is done making requests
        context.shutdown();
    }
}
