package com.skopjegeoguessr.springbatchdemo.repository;

import com.skopjegeoguessr.springbatchdemo.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, String> {
}
