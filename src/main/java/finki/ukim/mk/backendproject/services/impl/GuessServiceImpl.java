package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.GuessDto;
import finki.ukim.mk.backendproject.models.Guess;
import finki.ukim.mk.backendproject.repository.GuessRepository;
import finki.ukim.mk.backendproject.services.GuessService;
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
    public Optional<Guess> save(GuessDto guessDto) {
        Guess guess = new Guess();
        guess.setPlace(guessDto.getPlace());
        guess.setImage_url(guessDto.getImage_url());
        return Optional.of(this.guessRepository.save(guess));
    }

    @Override
    public void deleteById(String id) {
        guessRepository.deleteById(id);
    }
}
