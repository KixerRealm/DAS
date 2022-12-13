package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.GameType;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class LeaderboardRecord {
    @Id
    @GeneratedValue
    private String id;

    private String username;

    private Integer total;

    private String game_id;

    private GameType game_type;

    private String profile_pic;

    private LocalDateTime started_at;

    private LocalDateTime ended_at;

    public LeaderboardRecord() {}

    public LeaderboardRecord(String id, String username, Integer total, String game_id, GameType game_type, String profile_pic, LocalDateTime started_at, LocalDateTime ended_at) {
        this.id = id;
        this.username = username;
        this.total = total;
        this.game_id = game_id;
        this.game_type = game_type;
        this.profile_pic = profile_pic;
        this.started_at = started_at;
        this.ended_at = ended_at;
    }
}
