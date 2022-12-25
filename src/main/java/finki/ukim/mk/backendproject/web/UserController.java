package finki.ukim.mk.backendproject.web;


import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.security.JWTUser;
import finki.ukim.mk.backendproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) throws RuntimeException {
		return ResponseEntity.ok(userService.createUser(user));
	}

	@GetMapping("/identify")
	public ResponseEntity<UserDto> identify(JWTUser jwtUser) {
		return ResponseEntity.ok(userService.getUserById(jwtUser.getId()));
	}
}