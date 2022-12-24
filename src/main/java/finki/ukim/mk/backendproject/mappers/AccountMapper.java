package finki.ukim.mk.backendproject.mappers;

import finki.ukim.mk.backendproject.models.Account;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

	Account toEntity(UserRepresentation userRepresentation);
}
