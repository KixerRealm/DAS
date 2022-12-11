package com.skopjegeoguessr.springbatchdemo.config;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.springbatchdemo.model.AllData;
import com.skopjegeoguessr.springbatchdemo.model.AllDataDto;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseToFilteredDataMapper {
    BaseToFilteredDataMapper INSTANCE = Mappers.getMapper(BaseToFilteredDataMapper.class);

    @Mapping(target = "id", source = "placeId")
    @Mapping(target = "lat", source = "geometry.location.lat")
    @Mapping(target = "lng", source = "geometry.location.lng")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "types", source = "types")
    @Mapping(target = "photoReference", expression = "java(result.photos != null ? result.photos[0].photoReference : null)")
    AllData toDto(PlacesSearchResult result);
}
