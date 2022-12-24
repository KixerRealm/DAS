package finki.ukim.mk.backendproject.security.config;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
public class JwtDecoderConfig {

	@Value("${server.ssl.key-store}")
	private Resource trustStore;
	@Value("${server.ssl.key-store-password}")
	private String trustStorePassword;
	@Value("${jwt.jwk-set-uri}")
	private String jwkSetUri;

	@Bean
	org.springframework.security.oauth2.jwt.JwtDecoder jwtDecoder() throws CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		return NimbusJwtDecoder
				.withJwkSetUri(this.jwkSetUri)
				.restOperations(restTemplate())
				.build();
	}

	@Bean
	public RestTemplate restTemplate() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		SSLContext sslContext = new SSLContextBuilder()
				.loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
				.build();
		SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConFactory).build();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(requestFactory);
	}
}
