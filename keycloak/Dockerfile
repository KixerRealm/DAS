FROM quay.io/keycloak/keycloak:latest as builder

# Enable health and metrics support
ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true

# Configure a database vendor
ENV KC_DB=postgres

COPY realm.json /opt/keycloak/data/import/realm.json

WORKDIR /opt/keycloak
# for demonstration purposes only, please make sure to use proper certificates in production instead
#RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore
COPY server.keystore conf/server.keystore
RUN /opt/keycloak/bin/kc.sh build --features=docker,token-exchange

FROM quay.io/keycloak/keycloak:latest
COPY --from=builder /opt/keycloak/ /opt/keycloak/


# change these values to point to a running postgres instance
ENV KC_DB_URL=${KC_DB_URL}
ENV KC_DB_USERNAME=${KC_DB_USERNAME}
ENV KC_DB_PASSWORD=${KC_DB_PASSWORD}
ENV KC_HOSTNAME=${KC_HOSTNAME}
ENV KC_HTTPS_KEY_STORE_PASSWORD=${KC_HTTPS_KEY_STORE_PASSWORD}
ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]