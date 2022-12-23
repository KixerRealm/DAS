package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, String> {
    List<Place> findAllByType(PlaceType type);
}