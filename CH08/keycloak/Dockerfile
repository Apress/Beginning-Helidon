FROM quay.io/keycloak/keycloak:20.0.3

ENV KC_HEALTH_ENABLED=true

ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin
COPY ./beginning-helidon.json /opt/keycloak/data/import/beginning-helidon.json

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--http-port", "8979", "--import-realm"]