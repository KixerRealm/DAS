package finki.ukim.mk.backendproject.services.interfaces;

import finki.ukim.mk.backendproject.dtos.GuessDto;
import finki.ukim.mk.backendproject.enumerators.PlaceType;
import finki.ukim.mk.backendproject.models.Guess;
import finki.ukim.mk.backendproject.models.LeaderboardRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GuessService {
    List<Guess> findAll();
    //Guess findOrderByRand();

    Optional<Guess> findById(String id);

    Optional<Guess> save(GuessDto guessDto) ;

    void deleteById(String id);
}
