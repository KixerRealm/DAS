//package finki.ukim.mk.backendproject.services.interfaces;
//
//import finki.ukim.mk.backendproject.enumerators.GameType;
//import finki.ukim.mk.backendproject.models.GameAttempt;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//public interface GameAttemptService {
//    List<GameAttempt> findAll();
//    Optional<GameAttempt> findById(String id);
//
//    Optional<GameAttempt> save(String id, String email, GameType gameType, LocalDateTime started_at, LocalDateTime ended_at, Integer total);
//
//    void deleteById(String id);
//}
