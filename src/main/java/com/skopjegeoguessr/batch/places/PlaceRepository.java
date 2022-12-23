package com.skopjegeoguessr.batch.places;

import com.skopjegeoguessr.batch.places.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, String> {
}
