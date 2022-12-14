package finki.ukim.mk.backendproject.services.interfaces;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    Optional<Place> save(String id, Float lat, Float lng, String location_name, String photo_reference, PlaceType type);
    List<Place> findAll();
}
