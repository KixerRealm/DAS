package finki.ukim.mk.backendproject.services;

import finki.ukim.mk.backendproject.dtos.PlaceDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

	List<PlaceDto> findAll();

	List<PlaceDto> findAllByType(PlaceType type);

	PlaceDto findRandomPlaceByType(PlaceType placeType);

}
