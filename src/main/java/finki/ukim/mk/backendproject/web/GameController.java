package finki.ukim.mk.backendproject.web;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.services.interfaces.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/all")
    public List<Game> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{type}")
    public List<Game> findAllByType(@PathVariable PlaceType type){
        return gameService.findAllGamesByGameTypeOrderByPoints(type);
    }
}
