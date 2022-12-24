package finki.ukim.mk.backendproject.web;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.security.JWTUser;
import finki.ukim.mk.backendproject.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/game")
//@CrossOrigin("localhost:3000")
public class GameController {
	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/all")
	public List<Game> findAll() {
		return gameService.findAll();
	}

	@PostMapping("/start")
	public ResponseEntity start(@RequestBody GameDto gameDto, JWTUser userRepresentation) {
		System.out.println();
		this.gameService.save(gameDto, userRepresentation.getId());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{type}")
	public List<Game> findAllByType(@PathVariable PlaceType type) {
		return gameService.findAllGamesByGameTypeOrderByPoints(type);
	}
}
