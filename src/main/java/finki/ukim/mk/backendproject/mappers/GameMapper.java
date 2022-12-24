package finki.ukim.mk.backendproject.mappers;

import finki.ukim.mk.backendproject.dtos.GameDto;
import finki.ukim.mk.backendproject.models.Game;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {
	GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

	GameDto toDto(Game game);

}
