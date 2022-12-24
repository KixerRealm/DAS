package finki.ukim.mk.backendproject.dtos;

import finki.ukim.mk.backendproject.enums.PlaceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class GameDto {
    private String id;
    private PlaceType gameType;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private List<GuessDto> guesses;
    private Integer totalPoints;
}
