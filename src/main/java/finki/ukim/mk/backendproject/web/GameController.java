package finki.ukim.mk.backendproject.web;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.dtos.LeaderboardRecordDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.security.JWTUser;
import finki.ukim.mk.backendproject.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/game")
//@CrossOrigin("localhost:3000")
public class GameController {
	private final GameService gameService;

	@PostMapping("/start")
	public ResponseEntity<GameDto> start(@RequestBody GameDto gameDto, JWTUser userRepresentation) {
		return ResponseEntity.ok().body(this.gameService.startGame(gameDto, userRepresentation.getId()));
	}

	@PostMapping("/submit")
	public ResponseEntity<GameDto> submit(@RequestBody GameDto gameDto, JWTUser userRepresentation) {
		return ResponseEntity.ok().body(this.gameService.submitGame(gameDto, userRepresentation.getId()));
	}

	@PostMapping("/cancel")
	public ResponseEntity<Void> cancel(@RequestBody GameDto gameDto, JWTUser userRepresentation) {
		gameService.cancel(gameDto, userRepresentation.getId());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/leaderboards")
	public ResponseEntity<List<LeaderboardRecordDto>> leaderboards(@RequestParam("gameMode") PlaceType placeType) {
		return ResponseEntity.ok(gameService.leaderboards(placeType));
	}
}
