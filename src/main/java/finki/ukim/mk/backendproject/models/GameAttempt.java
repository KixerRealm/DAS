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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public LocalDateTime getStarted_at() {
        return started_at;
    }

    public void setStarted_at(LocalDateTime started_at) {
        this.started_at = started_at;
    }

    public LocalDateTime getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(LocalDateTime ended_at) {
        this.ended_at = ended_at;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
