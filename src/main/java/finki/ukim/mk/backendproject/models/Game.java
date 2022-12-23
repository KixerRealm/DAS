package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private String id;

    @Column(name = "player_id")
    private String playerId;

    @Column(name = "game_type")
    @Enumerated(EnumType.STRING)
    private PlaceType gameType;

    @Column(name = "startedAt")
    //@Temporal(TemporalType.DATE)
    private LocalDateTime started_at;

    @Column(name = "endedAt")
    //@Temporal(TemporalType.DATE)
    private LocalDateTime ended_at;

    @Column(name = "score")
    private int points;

}
