package finki.ukim.mk.backendproject.mappers;

import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.models.Account;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


	UserRepresentation toRepresentation(UserDto userDto);

	@Mapping(target = "id", source = "userRepresentation.id")
	UserDto toDto(UserRepresentation userRepresentation, Account account);
}
