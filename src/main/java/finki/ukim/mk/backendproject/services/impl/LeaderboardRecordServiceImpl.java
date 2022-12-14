package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.enumerators.GameType;
import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;
import finki.ukim.mk.backendproject.repository.LeaderboardRecordRepository;
import finki.ukim.mk.backendproject.services.interfaces.LeaderboardRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeaderboardRecordServiceImpl implements LeaderboardRecordService {
    private final LeaderboardRecordRepository leaderboardRecordRepository;

    public LeaderboardRecordServiceImpl(LeaderboardRecordRepository leaderboardRecordRepository) {
        this.leaderboardRecordRepository = leaderboardRecordRepository;
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
    public Optional<LeaderboardRecord> save(String id, String username, Integer total, String game_id, GameType game_type, String profile_pic, LocalDateTime started_at, LocalDateTime ended_at) {
        return Optional.of(leaderboardRecordRepository.save(new LeaderboardRecord(id, username, total, game_id, game_type ,profile_pic, started_at, ended_at)));
    }

    @Override
    public void deleteById(String id) {
        leaderboardRecordRepository.deleteById(id);
    }
}
