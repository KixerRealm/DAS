package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date started_at;

    @Column(name = "endedAt")
    @Temporal(TemporalType.DATE)
    private Date ended_at;

    @Column(name = "score")
    private Integer points;

}
