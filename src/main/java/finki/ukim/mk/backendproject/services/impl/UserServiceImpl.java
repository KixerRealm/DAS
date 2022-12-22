package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.models.LeaderboardRecord;
import finki.ukim.mk.backendproject.models.Place;
import finki.ukim.mk.backendproject.models.User;
import finki.ukim.mk.backendproject.repository.UserRepository;
import finki.ukim.mk.backendproject.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Place> save(String id, String username, String password, String email, String img_url, List<LeaderboardRecord> recordList) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
