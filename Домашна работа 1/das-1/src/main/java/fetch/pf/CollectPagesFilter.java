package fetch.pf;

import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import fetch.utility.Utilities;

import java.io.IOException;
import java.util.List;

public class CollectPagesFilter implements Filter<PlacesSearchResponse, List<PlacesSearchResponse>> {

	@Override
	public List<PlacesSearchResponse> execute(Object input) throws IOException, InterruptedException, ApiException {
		PlacesSearchResponse originalData = (PlacesSearchResponse) input;
		// Collect all pages
		List<PlacesSearchResponse> responses = new java.util.ArrayList<>(List.of(originalData));
		while (responses.get(responses.size() - 1).nextPageToken != null &&
				responses.get(responses.size() - 1).nextPageToken.length() != 0) {
			Thread.sleep(3000);
			responses.add(
					Utilities.wrapWithContext(context ->
							PlacesApi.textSearchQuery(
									context,
									"coffeeshops+skopje+macedonia"
							).pageToken(responses.get(responses.size() - 1).nextPageToken).await()
					)
			);
		}

		return responses;
	}

}
