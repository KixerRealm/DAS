package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.GameType;

import javax.persistence.ForeignKey;
import java.time.LocalDateTime;

public class LeaderboardRecord {
    private String username;

    private Integer total;

    private String game_id;

    private GameType game_type;

    private String profile_pic;

    private LocalDateTime started_at;

    private LocalDateTime ended_at;
}
