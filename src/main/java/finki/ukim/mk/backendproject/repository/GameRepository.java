package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {

}