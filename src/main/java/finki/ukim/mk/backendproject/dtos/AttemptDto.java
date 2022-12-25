package finki.ukim.mk.backendproject.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import finki.ukim.mk.backendproject.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptDto {
	private String id;
	private PlaceType gameType;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startedAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endedAt;
	private Integer totalPoints;
	private long minutes;
	private long seconds;
	private Integer placement;
}
