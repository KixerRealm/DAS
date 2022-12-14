package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "leaderboard_records")
public class LeaderboardRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String username;

    private Integer total;

    private String game_id;

    private GameType game_type;

    private String profile_pic;

    private LocalDateTime started_at;

    private LocalDateTime ended_at;

}
