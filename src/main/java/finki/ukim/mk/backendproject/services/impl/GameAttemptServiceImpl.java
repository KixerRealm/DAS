//package finki.ukim.mk.backendproject.services.impl;
//
//import finki.ukim.mk.backendproject.enumerators.GameType;
//import finki.ukim.mk.backendproject.models.GameAttempt;
//import finki.ukim.mk.backendproject.repository.GameAttemptRepository;
//import finki.ukim.mk.backendproject.services.interfaces.GameAttemptService;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
////@Service
//public class GameAttemptServiceImpl implements GameAttemptService {
//    private final GameAttemptRepository gameAttemptRepository;
//
//    public GameAttemptServiceImpl(GameAttemptRepository gameAttemptRepository) {
//        this.gameAttemptRepository = gameAttemptRepository;
//    }
//
//    @Override
//    public List<GameAttempt> findAll() {
//        return gameAttemptRepository.findAll();
//    }
//
//    @Override
//    public Optional<GameAttempt> findById(String id) {
//        return gameAttemptRepository.findById(id);
//    }
//
//    @Override
//    public Optional<GameAttempt> save(String id, String email, GameType gameType, LocalDateTime started_at, LocalDateTime ended_at, Integer total) {
//        return Optional.of(gameAttemptRepository.save(new GameAttempt(id,email, gameType, started_at, ended_at, total)));
//    }
//
//    @Override
//    public void deleteById(String id) {
//        gameAttemptRepository.deleteById(id);
//    }
//}
