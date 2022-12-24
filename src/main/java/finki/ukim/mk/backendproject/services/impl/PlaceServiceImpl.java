package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Place;
import finki.ukim.mk.backendproject.repository.PlaceRepository;
import finki.ukim.mk.backendproject.services.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Optional<Place> save(String id, Float lat, Float lng, String location_name, String photo_reference, PlaceType type) {
        return Optional.of(placeRepository.save(new Place(id, lat, lng, location_name, photo_reference,type)));
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public List<Place> findAllByType(PlaceType type) {
        return placeRepository.findAllByType(type);
    }

    @Override
    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }
}
