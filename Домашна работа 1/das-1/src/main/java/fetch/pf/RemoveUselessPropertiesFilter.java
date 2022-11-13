package fetch.pf;

import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResult;
import fetch.dtos.BaseGMDto;
import fetch.mappers.BaseGMDtoMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveUselessPropertiesFilter implements Filter<List<PlacesSearchResult>, List<BaseGMDto>> {
	@Override
	public List<BaseGMDto> execute(Object input) {
		List<PlacesSearchResult> results = (List<PlacesSearchResult>) input;

		// Only map out the properties that we need
		return results.stream().map(BaseGMDtoMapper.INSTANCE::toDto).collect(Collectors.toList());
	}
}
