package finki.ukim.mk.backendproject.services.interfaces;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> findAll();
    Optional<Game> findById(String id);

    Optional<Game> save(String gameId, String player_id, PlaceType placeType, LocalDateTime started_at, LocalDateTime ended_at, Integer points);

    void deleteById(String id);
}
