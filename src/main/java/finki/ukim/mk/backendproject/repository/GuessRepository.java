package finki.ukim.mk.backendproject.repository;

import finki.ukim.mk.backendproject.models.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuessRepository extends JpaRepository<Guess, String> {

    //List<Guess> findGuessByOrderByRandomLimit();
}