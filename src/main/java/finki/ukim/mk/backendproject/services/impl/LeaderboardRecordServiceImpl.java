package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;
import finki.ukim.mk.backendproject.models.User;
import finki.ukim.mk.backendproject.repository.GameRepository;
import finki.ukim.mk.backendproject.repository.LeaderboardRecordRepository;
import finki.ukim.mk.backendproject.repository.UserRepository;
import finki.ukim.mk.backendproject.services.interfaces.LeaderboardRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeaderboardRecordServiceImpl implements LeaderboardRecordService {
    private final LeaderboardRecordRepository leaderboardRecordRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public LeaderboardRecordServiceImpl(LeaderboardRecordRepository leaderboardRecordRepository,
                                        UserRepository userRepository,
                                        GameRepository gameRepository) {
        this.leaderboardRecordRepository = leaderboardRecordRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<LeaderboardRecord> findAll() {
        return leaderboardRecordRepository.findAll();
    }

    @Override
    public Optional<LeaderboardRecord> findById(String id) {
        return leaderboardRecordRepository.findById(id);
    }

    @Override
    public Optional<LeaderboardRecord> save(String id, String user_id, Integer total, String game_id,
                                            List<User> users, List<Game> games) {

        return Optional.of(new LeaderboardRecord(id, user_id, game_id, users, games));

    }

    @Override
    public void deleteById(String id) {
        leaderboardRecordRepository.deleteById(id);
    }
}
