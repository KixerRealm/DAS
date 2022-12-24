package finki.ukim.mk.backendproject.mappers;

import finki.ukim.mk.backendproject.dtos.PlaceDto;
import finki.ukim.mk.backendproject.models.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaceMapper {

	PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

	@Mapping(target = "location.lat", source = "lat")
	@Mapping(target = "location.lng", source = "lng")
	PlaceDto toDto(Place place);
}
