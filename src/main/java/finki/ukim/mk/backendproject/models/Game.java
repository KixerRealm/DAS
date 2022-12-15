package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String gameId;

    @Column(name = "user_id")
    private String player_id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @Column(name = "startedAt")
    private LocalDateTime started_at;

    @Column(name = "endedAt")
    private LocalDateTime ended_at;

    @Column(name = "score")
    private Integer points;

}
