package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.LeaderboardRecordDto;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;
import finki.ukim.mk.backendproject.repository.LeaderboardRecordRepository;
import finki.ukim.mk.backendproject.services.GameService;
import finki.ukim.mk.backendproject.services.LeaderboardRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaderboardRecordServiceImpl implements LeaderboardRecordService {
    private final LeaderboardRecordRepository leaderboardRecordRepository;
    private final GameService gameService;

    public LeaderboardRecordServiceImpl(LeaderboardRecordRepository leaderboardRecordRepository,
                                        GameService gameService) {
        this.leaderboardRecordRepository = leaderboardRecordRepository;
        this.gameService = gameService;
    }

    @Override
    public List<LeaderboardRecord> findAll() {
        return this.leaderboardRecordRepository.findAll();
    }

    @Override
    public Optional<LeaderboardRecord> findById(String id) {
        return this.leaderboardRecordRepository.findById(id);
    }

    @Override
    public Optional<LeaderboardRecord> save(LeaderboardRecordDto leaderboardRecordDto) {

        return Optional.of(new LeaderboardRecord());

    }

    @Override
    public void deleteById(String id) {
        leaderboardRecordRepository.deleteById(id);
    }
}
