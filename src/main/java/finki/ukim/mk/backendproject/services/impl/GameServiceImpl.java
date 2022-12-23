package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.models.User;
import finki.ukim.mk.backendproject.repository.GameRepository;
import finki.ukim.mk.backendproject.services.interfaces.GameService;
import finki.ukim.mk.backendproject.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
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
        User user = this.userService.findByEmail(gameDto.getEmail());
        game.setPlayerId(user.getId());
        game.setGameType(gameDto.getGameType());
        game.setStarted_at(LocalDateTime.from(LocalDateTime.now()));
        this.gameRepository.save(game);
        return Optional.of(game);
    }

    @Override
    public void deleteById(String id) {
        this.gameRepository.deleteById(id);
    }

    @Override
    public List<Game> findAllGamesByGameTypeOrderByPoints(PlaceType type) {
        if(type==PlaceType.ALL){
            return this.gameRepository.findByOrderByTotalPoints();
        }else {
            return this.gameRepository.findAllByGameTypeOrderByTotalPoints(type);
        }
    }
}
