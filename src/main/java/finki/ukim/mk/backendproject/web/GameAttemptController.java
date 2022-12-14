package finki.ukim.mk.backendproject.web;

import finki.ukim.mk.backendproject.models.GameAttempt;
import finki.ukim.mk.backendproject.services.impl.GameAttemptServiceImpl;
import finki.ukim.mk.backendproject.services.interfaces.GameAttemptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/game_attempt/game/start")
public class GameAttemptController {
    private final GameAttemptService gameAttemptService;

    public GameAttemptController(GameAttemptService gameAttemptService) {
        this.gameAttemptService = gameAttemptService;
    }

    @GetMapping("/all")
    public List<GameAttempt> findAll(){
        return this.gameAttemptService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<GameAttempt> gameAttempt(@PathVariable String id){
        return gameAttemptService.findById(id);
    }
}
