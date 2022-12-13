package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.GameType;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class GameAttempt {

    @Id
    private String id;

    private String email;

    private GameType gameType;

    private LocalDateTime started_at;

    private LocalDateTime ended_at;

    private Integer total;

    public GameAttempt(String id, String email, GameType gameType, LocalDateTime started_at, LocalDateTime ended_at, Integer total) {
        this.id = id;
        this.email = email;
        this.gameType = gameType;
        this.started_at = started_at;
        this.ended_at = ended_at;
        this.total = total;
    }

    public GameAttempt() {

    }
}
