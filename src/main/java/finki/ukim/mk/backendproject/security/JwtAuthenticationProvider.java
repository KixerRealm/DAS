package finki.ukim.mk.backendproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final JwtDecoder jwtDecoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BearerTokenAuthenticationToken token = (BearerTokenAuthenticationToken) authentication;
		System.out.println("Called authenticate :P");
		Jwt jwt;
		try {
			jwt = this.jwtDecoder.decode(token.getToken());
			System.out.println("decoded...");
		} catch (JwtValidationException ex) {
			return null;
		}
		System.out.println(jwt.getClaims().get("authorities"));
		JWTUser user = new JWTUser(jwt, Collections.emptyList());
		user.setId(jwt.getClaimAsString("sub"));
		return user;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(BearerTokenAuthenticationToken.class);
	}

}
