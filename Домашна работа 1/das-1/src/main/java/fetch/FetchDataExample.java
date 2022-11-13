package fetch;

import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import fetch.dtos.BaseGMDto;
import fetch.pf.ShopsPipe;
import fetch.utility.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FetchDataExample {

	public static void main(String[] args) throws IOException, InterruptedException, ApiException {
		ArrayList<PlacesSearchResponse> places = new ArrayList<>();
		places.add(
				Utilities.wrapWithContext(context ->
						PlacesApi.textSearchQuery(
								context,
								"skopje+macedonia"
						).type(PlaceType.CAFE).await()
				)
		);

		places.add(
				Utilities.wrapWithContext(context ->
						PlacesApi.textSearchQuery(
								context,
								"landmarks+skopje+macedonia"
						).await()
				)
		);
		Utilities.writeToFile(places, "original.json");


		List<BaseGMDto> results = Collections.synchronizedList(new ArrayList<>());
		ArrayList<CompletableFuture<Void>> futures = new ArrayList<>();
		for (PlacesSearchResponse place : places) {
			futures.add(
					CompletableFuture.runAsync(() -> {
						ShopsPipe pipe = new ShopsPipe();
						pipe.addStandardFilters();
						try {
							results.addAll(pipe.runFilters(place));
						} catch (Exception ignored) {
						}
					})
			);
		}

		CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
		Utilities.writeToFile(results, "final.json");
	}

}
