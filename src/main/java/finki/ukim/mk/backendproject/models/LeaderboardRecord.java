package finki.ukim.mk.backendproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "leaderboard_records")
public class LeaderboardRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String user_id;

    private String game_id;

    @ManyToMany
    private List<User> userList;

    @ManyToMany
    private List<Game> gameList;

}
