package finki.ukim.mk.backendproject.services.interfaces;

import finki.ukim.mk.backendproject.enumerators.GameType;
import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LeaderboardRecordService {
    List<LeaderboardRecord> findAll();
    Optional<LeaderboardRecord> findById(String id);

    Optional<LeaderboardRecord> save(String id, String username, Integer total, String game_id, GameType game_type,
                                     String profile_pic, LocalDateTime started_at, LocalDateTime ended_at);

    void deleteById(String id);
}
