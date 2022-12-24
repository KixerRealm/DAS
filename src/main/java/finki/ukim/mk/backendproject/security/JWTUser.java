package finki.ukim.mk.backendproject.security;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class JWTUser extends JwtAuthenticationToken {

	private String id;
	public JWTUser(Jwt jwt) {
		super(jwt);
	}

	public JWTUser(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
		super(jwt, authorities);
	}

	public JWTUser(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String name) {
		super(jwt, authorities, name);
	}
}
