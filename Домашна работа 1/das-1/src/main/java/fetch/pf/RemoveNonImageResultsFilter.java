package fetch.pf;

import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResult;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveNonImageResultsFilter implements Filter<List<PlacesSearchResult>, List<PlacesSearchResult>> {
	@Override
	public List<PlacesSearchResult> execute(Object input) {
		List<PlacesSearchResult> data = (List<PlacesSearchResult>) input;
		return data.stream().filter(item -> item.photos != null).collect(Collectors.toList());
	}
}
