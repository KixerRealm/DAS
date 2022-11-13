package fetch.utility;

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

	public static <T> void writeToFile(T results, String fileName) throws IOException {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, type, jsonDeserializationContext) -> {
			try{
				return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			} catch (DateTimeParseException e){
				return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
			}
		}).setPrettyPrinting().create();

		FileWriter writer = new FileWriter("E:\\Programming\\das-1\\src\\main\\resources\\" + fileName);
		gson.toJson(results, writer);
		writer.flush();
	}

}
