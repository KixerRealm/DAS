package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.GameType;

import java.time.LocalDateTime;

public class GameAttempt {
    private String id;

    private String email;
    private GameType gameType;
    private LocalDateTime started_at;
    private LocalDateTime ended_at;
    private Guess[] guess;
    private int total;
}
