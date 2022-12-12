package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.GameType;

import java.time.LocalDateTime;
import java.util.List;

public class GameAttempt {
    private String id;

    private String email;

    private GameType gameType;

    private LocalDateTime started_at;

    private LocalDateTime ended_at;

    private List<Guess> guess;

    private Integer total;
}
