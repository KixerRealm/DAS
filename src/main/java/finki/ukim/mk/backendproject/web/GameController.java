package finki.ukim.mk.backendproject.web;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.models.Guess;
import finki.ukim.mk.backendproject.services.interfaces.GameService;
import finki.ukim.mk.backendproject.services.interfaces.GuessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/game")
//@CrossOrigin("localhost:3000")
public class GameController {
    private final GameService gameService;
    private final GuessService guessService;

    public GameController(GameService gameService, GuessService guessService) {
        this.gameService = gameService;
        this.guessService = guessService;
    }

    @GetMapping("/all")
    public List<Game> findAll() {
        return gameService.findAll();
    }

    @PostMapping("/start")
    public ResponseEntity findGuess(@RequestBody GameDto gameDto){
        this.gameService.save(gameDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/next-guess")
    public List<Guess> findNextGuess(){
        return this.guessService.findAll();
    }

    @GetMapping("/{type}")
    public List<Game> findAllByType(@PathVariable PlaceType type){
        return gameService.findAllGamesByGameTypeOrderByPoints(type);
    }
}
