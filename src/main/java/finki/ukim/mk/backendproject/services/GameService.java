package finki.ukim.mk.backendproject.services;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> findAll();
    Optional<Game> findById(String id);

    Optional<Game> save(GameDto gameDto, String userId);

    void deleteById(String id);

    List<Game> findAllGamesByGameTypeOrderByPoints(PlaceType type);
}
