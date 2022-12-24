package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, String> {
    List<Game> findAllByGameTypeOrderByTotalPoints(PlaceType type);
    List<Game> findByOrderByTotalPoints();
}