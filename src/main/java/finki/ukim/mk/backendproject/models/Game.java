package finki.ukim.mk.backendproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

//@Entity
public class Game {

    @Id
    private String gameId;

    private String email;

    private LocalDateTime started_at;

    private LocalDateTime ended_at;

    private Integer points;

    public Game() {}

    public Game(String gameId, String email, LocalDateTime started_at, LocalDateTime ended_at, Integer points) {
        this.gameId = gameId;
        this.email = email;
        this.started_at = started_at;
        this.ended_at = ended_at;
        this.points = points;
    }
}
