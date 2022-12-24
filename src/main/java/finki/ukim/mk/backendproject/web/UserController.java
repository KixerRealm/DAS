package finki.ukim.mk.backendproject.web;


import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;


    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
}