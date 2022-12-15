package finki.ukim.mk.backendproject.services.interfaces;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;
import finki.ukim.mk.backendproject.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LeaderboardRecordService {
    List<LeaderboardRecord> findAll();
    Optional<LeaderboardRecord> findById(String id);

    Optional<LeaderboardRecord> save(String id, String user_id, Integer total, String game_id,
                                     List<User> users, List<Game> games);

    void deleteById(String id);
}
