package finki.ukim.mk.backendproject.services.interfaces;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Game;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;
import finki.ukim.mk.backendproject.models.Place;
import finki.ukim.mk.backendproject.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Place> save(String id, String username, String password, String email, String img_url, List<LeaderboardRecord> recordList);
    List<User> findAll();
}
