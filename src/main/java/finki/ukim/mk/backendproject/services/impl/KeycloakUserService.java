package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.mappers.UserMapper;
import finki.ukim.mk.backendproject.models.User;
import finki.ukim.mk.backendproject.security.Credentials;
import finki.ukim.mk.backendproject.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class KeycloakUserService implements UserService {

	private final UsersResource keycloak;

	@Override
	public List<UserDto> findAll() {
		return keycloak.list().stream().map(UserMapper.INSTANCE::toDto).collect(Collectors.toList());
	}

	@Override
	public UserDto createUser(UserDto user) {
		CredentialRepresentation credential = Credentials.createPasswordCredentials(user.getPassword());
		List<CredentialRepresentation> creds = Collections.singletonList(credential);

		UserRepresentation userRepresentation = UserMapper.INSTANCE.toRepresentation(user);
		userRepresentation.setCredentials(creds);
		userRepresentation.setEnabled(true);
		keycloak.create(userRepresentation);

		return UserMapper.INSTANCE.toDto(keycloak.searchByUsername(user.getUsername(), true).get(0));
	}

	@Override
	public UserDto getUserById(String id) {
		return UserMapper.INSTANCE.toDto(keycloak.get(id).toRepresentation());
	}

	@Override
	public UserDto findByEmail(String email) {
		return UserMapper.INSTANCE.toDto(keycloak.searchByEmail(email, true).get(0));
	}
}
