package finki.ukim.mk.backendproject.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import finki.ukim.mk.backendproject.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardRecordDto {

	private String gameId;
	private String username;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timeStarted;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timeCompleted;
	private PlaceType type;
	private String profilePictureUrl;
	private Integer totalPoints;
}
