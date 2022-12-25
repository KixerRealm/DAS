package finki.ukim.mk.backendproject.web;

//import finki.ukim.mk.backendproject.services.interfaces.GameAttemptService;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.dtos.PlaceDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Place;
import finki.ukim.mk.backendproject.services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/places")
public class PlaceController {
	private final PlaceService placeService;

	@PostMapping(value = "/next-guess")
	public ResponseEntity<PlaceDto> findNextGuess(@RequestBody GameDto placeDto) {
		return ResponseEntity.ok(placeService.findRandomPlaceByType(placeDto.getGameType()));
	}
}
