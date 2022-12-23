package finki.ukim.mk.backendproject.models;

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
    @Column(name = "leaderboard_record_id")
    private String id;

    @Column(name = "player_id")
    private String playerId;

    @Column(name = "player_username")
    private String username;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "time_started")
    private LocalDateTime timeStarted;

    @Column(name = "time_completed")
    private LocalDateTime timeCompleted;

    @Column(name = "total")
    private int total;

}
