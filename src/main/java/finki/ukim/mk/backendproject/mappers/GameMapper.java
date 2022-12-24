package finki.ukim.mk.backendproject.mappers;

import finki.ukim.mk.backendproject.dtos.AttemptDto;
import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.dtos.LeaderboardRecordDto;
import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.models.Account;
import finki.ukim.mk.backendproject.models.Game;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.temporal.ChronoUnit;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {
	GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

	@Mapping(target = "placement", source = "endingPlacement")
	GameDto toDto(Game game);

	@Mapping(target = "gameId", source = "game.id")
	@Mapping(target = "timeStarted", source = "game.startedAt")
	@Mapping(target = "timeCompleted", source = "game.endedAt")
	@Mapping(target = "type", source = "game.gameType")
	LeaderboardRecordDto toLeaderboardDto(GameDto game, UserDto user);

	AttemptDto toAttemptDto(GameDto gameDto);

	@AfterMapping
	default void setTimeTaken(GameDto gameDto, @MappingTarget AttemptDto attemptDto) {
		long seconds = ChronoUnit.SECONDS.between(gameDto.getStartedAt(), gameDto.getEndedAt());
		attemptDto.setMinutes(Math.floorDiv(seconds, 60));
		attemptDto.setSeconds(Math.floorMod(seconds, 60));
	}

}
