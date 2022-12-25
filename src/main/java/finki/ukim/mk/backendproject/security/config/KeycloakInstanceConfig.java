package finki.ukim.mk.backendproject.security.config;

import org.apache.http.ssl.SSLContextBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
public class KeycloakInstanceConfig {

	@Value("${sko.keycloak.url}")
	private String serverUrl;
	@Value("${sko.keycloak.realm}")
	private String realm;
	@Value("${sko.keycloak.client.id}")
	private String clientId;
	@Value("${sko.keycloak.client.secret}")
	private String clientSecret;

	@Value("${server.ssl.key-store}")
	private Resource trustStore;
	@Value("${server.ssl.key-store-password}")
	private String trustStorePassword;

	@Bean
	public Client client() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		SSLContext sslContext = new SSLContextBuilder()
				.loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
				.build();
		return ResteasyClientBuilder.newBuilder().sslContext(sslContext).build();
	}

	@Bean
	public UsersResource keycloak() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		return KeycloakBuilder.builder()
				.serverUrl(serverUrl)
				.realm(realm)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
				.clientId(clientId)
				.clientSecret(clientSecret)
				.resteasyClient(client())
				.build().realm(realm).users();
	}
}
