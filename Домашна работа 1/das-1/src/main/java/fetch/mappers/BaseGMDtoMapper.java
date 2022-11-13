package fetch.mappers;

import com.google.maps.model.PlacesSearchResult;
import fetch.dtos.BaseGMDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseGMDtoMapper {

	BaseGMDtoMapper INSTANCE = Mappers.getMapper(BaseGMDtoMapper.class);

	@Mapping(target = "id", source = "placeId")
	@Mapping(target = "lat", source = "geometry.location.lat")
	@Mapping(target = "lng", source = "geometry.location.lng")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "types", source = "types")
	@Mapping(target = "photoReference", expression = "java(result.photos != null ? result.photos[0].photoReference : null)")
	BaseGMDto toDto(PlacesSearchResult result);

}
