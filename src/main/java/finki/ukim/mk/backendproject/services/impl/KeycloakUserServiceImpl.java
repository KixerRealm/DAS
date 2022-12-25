package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.UserDto;
import finki.ukim.mk.backendproject.mappers.AccountMapper;
import finki.ukim.mk.backendproject.mappers.UserMapper;
import finki.ukim.mk.backendproject.models.Account;
import finki.ukim.mk.backendproject.repository.AccountRepository;
import finki.ukim.mk.backendproject.security.Credentials;
import finki.ukim.mk.backendproject.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class KeycloakUserServiceImpl implements UserService {

	private final UsersResource keycloak;

	private final AccountRepository accountRepository;

	@Override
	public List<UserDto> findAll() {
		List<Account> accounts = accountRepository.findAll();
		List<UserRepresentation> keycloakUsers = keycloak.list();
		List<Map.Entry<Account, UserRepresentation>> zipped =
				IntStream.range(0, Math.min(accounts.size(), keycloakUsers.size()))
						.mapToObj(i -> Map.entry(accounts.get(i), keycloakUsers.get(i)))
						.toList();

		return zipped.stream().map(pair -> UserMapper.INSTANCE.toDto(pair.getValue(), pair.getKey())).collect(Collectors.toList());
	}

	@Override
	public UserDto createUser(UserDto user) throws RuntimeException {
		CredentialRepresentation credential = Credentials.createPasswordCredentials(user.getPassword());
		List<CredentialRepresentation> creds = Collections.singletonList(credential);

		UserRepresentation userRepresentation = UserMapper.INSTANCE.toRepresentation(user);
		userRepresentation.setCredentials(creds);
		userRepresentation.setEnabled(true);
		Response result = keycloak.create(userRepresentation);
		if(!result.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
			HashMap<String, String> resultMap = result.readEntity(HashMap.class);
			if(resultMap.containsKey("errorMessage")) {
				throw new RuntimeException(resultMap.get("errorMessage"));
			}
		}
		UserRepresentation keycloakUser = keycloak.searchByUsername(user.getUsername(), true).get(0);
		// TODO: Update so we use a separate AccountService
		Account account = AccountMapper.INSTANCE.toEntity(keycloakUser);
		account.setProfilePictureUrl(user.getProfilePictureUrl());
		accountRepository.save(account);

		return UserMapper.INSTANCE.toDto(keycloakUser, account);
	}

	@Override
	public UserDto getUserById(String id) {
		Optional<Account> account = accountRepository.findById(id);
		return account.map(value -> UserMapper.INSTANCE.toDto(keycloak.get(id).toRepresentation(), value)).orElseThrow(() -> new RuntimeException("User only exists in Keycloak. Please contact the administrators of the site."));

	}

	@Override
	public Account getAccountById(String id) {
		return accountRepository.findById(id).orElse(null);
	}

	@Override
	public UserDto findByEmail(String email) {
		UserRepresentation keycloakUser = keycloak.searchByEmail(email, true).get(0);
		Optional<Account> account = accountRepository.findById(keycloakUser.getId());
		return account.map(item -> UserMapper.INSTANCE.toDto(keycloakUser, item)).orElse(null);
	}
}
