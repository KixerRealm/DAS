package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.repository.GameRepository;
import finki.ukim.mk.backendproject.services.interfaces.GameService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> findById(String id) {
        return gameRepository.findById(id);
    }

    @Override
    public Optional<Game> save(String gameId, String player_id, PlaceType placeType, LocalDateTime started_at, LocalDateTime ended_at, Integer points) {
        return Optional.of(gameRepository.save(new Game(gameId, player_id, placeType, started_at, ended_at, points)));
    }

    @Override
    public void deleteById(String id) {
        gameRepository.deleteById(id);
    }

    @Override
    public List<Game> findAllGamesByGameTypeOrderByPoints(PlaceType type) {
        return gameRepository.findALlByPlaceTypeOrderByPoints(type);
    }
}
