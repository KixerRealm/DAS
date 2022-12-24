package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.mappers.GameMapper;
import finki.ukim.mk.backendproject.models.Account;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.repository.GameRepository;
import finki.ukim.mk.backendproject.services.GameService;
import finki.ukim.mk.backendproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;

    @Override
    public GameDto startGame(GameDto gameDto, String userId) {
        Game game = new Game();
        Account account = this.userService.getAccountById(userId);
        game.setStartedAt(LocalDateTime.now());
        game.setAccount(account);
        game.setGameType(gameDto.getGameType());
        this.gameRepository.save(game);
        return GameMapper.INSTANCE.toDto(game);
    }

    @Override
    public GameDto submitGame(GameDto game, String userId) {
        Game entity = gameRepository.findById(game.getId()).orElseThrow();
        entity.setTotalPoints(game.getTotalPoints());
        entity.setEndedAt(LocalDateTime.now());
        // TODO: Add async validation of information/guesses
        gameRepository.save(entity);
        return GameMapper.INSTANCE.toDto(entity);
    }

    @Override
    public void cancel(GameDto game, String userId) {
        gameRepository.deleteById(game.getId());
    }
}
