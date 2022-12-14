package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.models.Guess;
import finki.ukim.mk.backendproject.repository.GuessRepository;
import finki.ukim.mk.backendproject.services.interfaces.GuessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuessServiceImpl implements GuessService {
    private final GuessRepository guessRepository;

    public GuessServiceImpl(GuessRepository guessRepository) {
        this.guessRepository = guessRepository;
    }

    @Override
    public List<Guess> findAll() {
        return guessRepository.findAll();
    }

    @Override
    public Optional<Guess> findById(String id) {
        return guessRepository.findById(id);
    }

    @Override
    public Optional<Guess> save(String id, String location, String image) {
        return Optional.of(guessRepository.save(new Guess(id, location, image)));
    }

    @Override
    public void deleteById(String id) {
        guessRepository.deleteById(id);
    }
}
