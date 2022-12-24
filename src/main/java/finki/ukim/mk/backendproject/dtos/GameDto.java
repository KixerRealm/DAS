package finki.ukim.mk.backendproject.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	private String userId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startedAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endedAt;
	private List<GuessDto> guesses;
	private Integer totalPoints;

	private Integer placement;
}
