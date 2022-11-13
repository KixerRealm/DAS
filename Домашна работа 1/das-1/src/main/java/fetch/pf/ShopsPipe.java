package fetch.pf;

import com.google.maps.model.PlacesSearchResponse;
import fetch.dtos.BaseGMDto;

import java.util.List;

public class ShopsPipe extends BasePipe<PlacesSearchResponse, List<BaseGMDto>> {

	public void addStandardFilters() {
		addFilter(new CollectPagesFilter());
		addFilter(new OnlyResultsFilter());
		addFilter(new RemoveNonImageResultsFilter());
		addFilter(new RemoveUselessPropertiesFilter());
	}

}
