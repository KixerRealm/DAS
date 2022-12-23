package finki.ukim.mk.backendproject.services.interfaces;

import finki.ukim.mk.backendproject.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User createUser(User user);

    Optional<User> getUserById(String id);

    User findByEmail(String email);
}
