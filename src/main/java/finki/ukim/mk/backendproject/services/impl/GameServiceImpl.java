package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.repository.GameRepository;
import finki.ukim.mk.backendproject.services.interfaces.GameService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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
        return this.gameRepository.findAll();
    }

    @Override
    public Optional<Game> findById(String id) {
        return this.gameRepository.findById(id);
    }

    @Override
    public Optional<Game> save(GameDto gameDto) {
        Game game = new Game();
        game.setGameType(gameDto.getGameType());
        game.setStarted_at(gameDto.getStarted_at());
        game.setEnded_at(gameDto.getEnded_at());
        game.setPoints(gameDto.getPoints());
        return Optional.of(this.gameRepository.save(game));
    }

    @Override
    public void deleteById(String id) {
        this.gameRepository.deleteById(id);
    }

    @Override
    public List<Game> findAllGamesByGameTypeOrderByPoints(PlaceType type) {
        if(type==PlaceType.ALL){
            return this.gameRepository.findByOrderByPoints();
        }else {
            return this.gameRepository.findAllByGameTypeOrderByPoints(type);
        }
    }
}
