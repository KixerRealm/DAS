package finki.ukim.mk.backendproject.mappers;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.dtos.LeaderboardRecordDto;
import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.models.Account;
import finki.ukim.mk.backendproject.models.Game;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {
	GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

	GameDto toDto(Game game);

	@Mapping(target = "gameId", source = "game.id")
	@Mapping(target = "timeStarted", source = "game.startedAt")
	@Mapping(target = "timeCompleted", source = "game.endedAt")
	@Mapping(target = "type", source = "game.gameType")
	LeaderboardRecordDto toLeaderboardDto(GameDto game, UserDto user);

}
