package finki.ukim.mk.backendproject.services.interfaces;

import finki.ukim.mk.backendproject.enumerators.GameType;
import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.GameAttempt;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GameAttemptService {
    List<GameAttempt> findAll();
    Optional<LeaderboardRecord> findById(String id);

    Optional<LeaderboardRecord> save(String id, String email, GameType gameType, LocalDateTime started_at, LocalDateTime ended_at, Integer total);

    void deleteById(String id);
}
