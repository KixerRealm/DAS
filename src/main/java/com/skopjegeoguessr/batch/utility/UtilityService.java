package com.skopjegeoguessr.batch.utility;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UtilityService {

    @Value("${gcp-key}")
    public String gcpApiKey;

    public PlacesSearchResponse wrapWithContext(ContextMethod methods) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(gcpApiKey).build();
        PlacesSearchResponse response = methods.execute(context);
        context.shutdown();
        return response;
    }
}