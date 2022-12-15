package com.skopjegeoguessr.batch.repository;

import com.skopjegeoguessr.batch.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, String> {
}
