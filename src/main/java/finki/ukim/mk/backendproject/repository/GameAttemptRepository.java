package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.models.GameAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameAttemptRepository extends JpaRepository<GameAttempt, String> {
}