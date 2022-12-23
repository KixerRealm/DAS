package finki.ukim.mk.backendproject.dtos;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GameDto {
    private String email;
    private PlaceType gameType;
    //private LocalDateTime started_at;
    //private LocalDateTime ended_at;
    //private Integer points;


}
