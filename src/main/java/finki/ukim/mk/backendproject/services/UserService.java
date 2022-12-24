package finki.ukim.mk.backendproject.services;

import finki.ukim.mk.backendproject.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    UserDto createUser(UserDto user);

    UserDto getUserById(String id);

    UserDto findByEmail(String email);
}
