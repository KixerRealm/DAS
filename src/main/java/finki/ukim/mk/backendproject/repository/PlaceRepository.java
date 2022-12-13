package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, String> {
}