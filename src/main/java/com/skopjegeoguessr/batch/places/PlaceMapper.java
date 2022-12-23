package com.skopjegeoguessr.batch.places;

import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.batch.places.model.Place;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaceMapper {
    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    @Mapping(target = "id", source = "placeId")
    @Mapping(target = "lat", source = "geometry.location.lat")
    @Mapping(target = "lng", source = "geometry.location.lng")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "types", source = "types")
    @Mapping(target = "photoReference", expression = "java(result.photos != null ? result.photos[0].photoReference : null)")
    Place toEntity(PlacesSearchResult result);
}
