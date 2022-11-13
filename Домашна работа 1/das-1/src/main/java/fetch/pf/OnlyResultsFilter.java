package fetch.pf;

import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OnlyResultsFilter implements Filter<List<PlacesSearchResponse>, List<PlacesSearchResult>> {
	@Override
	public List<PlacesSearchResult> execute(Object input) {
		List<PlacesSearchResponse> responses = (List<PlacesSearchResponse>) input;
		// Map out the results that we actually need
		return responses.stream()
				.map(response -> Arrays.asList(response.results.clone()))
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}
}
