#!/bin/bash

# admin user
export KEYCLOAK_ADMIN=admin
export KEYCLOAK_ADMIN_PASSWORD=admin

KEYCLOAK_VERSION=20.0.3
KEYCLOAK_DIR=keycloak-${KEYCLOAK_VERSION}
KEYCLOAK_PORT=8979
REALM_NAME="beginning-helidon"
CLIENT_NAME="$REALM_NAME-client"
HELIDON_APP_URL='http://castle.local:8080'
KC_URL="http://keycloak.local:$KEYCLOAK_PORT"
kcadm="./${KEYCLOAK_DIR}/bin/kcadm.sh"
kc="./${KEYCLOAK_DIR}/bin/kc.sh"

function on_close() {
    echo "Killing Keycloak"
    pkill -P $$
    echo "Keycloak is dead, exiting"
    exit 0;
}

trap 'on_close' SIGINT

# Download and extract Keycloak 19.0.2 in to the same directory ./keycloak-19.0.2
# https://github.com/keycloak/keycloak/releases/download/19.0.2/keycloak-19.0.2.zip
if [ ! -d ${KEYCLOAK_DIR} ]; then
  wget https://github.com/keycloak/keycloak/releases/download/${KEYCLOAK_VERSION}/keycloak-${KEYCLOAK_VERSION}.zip
  unzip keycloak-${KEYCLOAK_VERSION}.zip
  rm keycloak-${KEYCLOAK_VERSION}.zip
fi

# Start Keycloak
./${KEYCLOAK_DIR}/bin/kc.sh start-dev \
--http-port $KEYCLOAK_PORT &

# Wait until Keycloak starts
timeout 22 bash -c 'until printf "" 2>>/dev/null >>/dev/tcp/$0/$1; do sleep 1; done' localhost $KEYCLOAK_PORT
echo

$kcadm config credentials --server ${KC_URL} --realm master --user admin --password admin

# Create fresh realm
$kcadm  delete realms/${REALM_NAME}
$kcadm create realms -s realm=${REALM_NAME} -s enabled=true

# Create client
CID=$($kcadm create clients -r ${REALM_NAME} -i  -f - << EOF
{
  "protocol": "openid-connect",
  "clientId": "${CLIENT_NAME}",
  "publicClient": false,
  "alwaysDisplayInConsole": true,
  "redirectUris": [
       "${HELIDON_APP_URL}/*"
  ],
  "attributes": {
    "post.logout.redirect.uris": "${HELIDON_APP_URL}/*",
    "backchannel.logout.session.required": "true"
  }
}
EOF
)

$kcadm create -x "client-scopes" -r ${REALM_NAME} -s name=kingdom-jwt-scope -s protocol=openid-connect
CUSTOM_JWT_AUDIENCE_SCOPE_ID=$($kcadm get client-scopes --fields id,name -r ${REALM_NAME} \
| jq -r '.[] | select(.name=="kingdom-jwt-scope").id')
$kcadm create client-scopes/$CUSTOM_JWT_AUDIENCE_SCOPE_ID/protocol-mappers/models -r ${REALM_NAME} -f - << 'EOF'
{
  "protocol": "openid-connect",
  "name": "kingdom-custom-audience",
  "protocolMapper": "oidc-audience-mapper",
  "config": {
   "access.token.claim": "true",
   "id.token.claim": "true",
   "included.client.audience": "",
   "included.custom.audience": "kingdom-audience"
  }
}
EOF

JWT_SCOPE_ID=$($kcadm get client-scopes --fields id,name -r ${REALM_NAME} \
| jq -r '.[] | select(.name=="microprofile-jwt").id')
# Enable JWT scopes for client
$kcadm delete clients/${CID}/optional-client-scopes/${JWT_SCOPE_ID} -r ${REALM_NAME}
$kcadm delete clients/${CID}/optional-client-scopes/${CUSTOM_JWT_AUDIENCE_SCOPE_ID} -r ${REALM_NAME}
$kcadm update clients/${CID}/default-client-scopes/${JWT_SCOPE_ID} -r ${REALM_NAME}
$kcadm update default-default-client-scopes/${CUSTOM_JWT_AUDIENCE_SCOPE_ID} -r ${REALM_NAME}
$kcadm update clients/${CID}/default-client-scopes/${CUSTOM_JWT_AUDIENCE_SCOPE_ID} -r ${REALM_NAME}

# Create client roles
$kcadm create roles -r ${REALM_NAME} -s name=gate-keeper -s 'description=Gate keeper role'
$kcadm create roles -r ${REALM_NAME} -s name=flag-keeper -s 'description=Flag keeper role'
$kcadm create roles -r ${REALM_NAME} -s name=warden -s 'description=Warden of the castle role'

#Create users and assign roles
$kcadm create users -s username=gyles -s 'attributes.firstName=Gyles' -s 'attributes.lastName=Fraffolk' -s enabled=true -r ${REALM_NAME}
$kcadm create users -s username=alad -s 'attributes.firstName=Alad' -s 'attributes.lastName=Keo' -s enabled=true -r ${REALM_NAME}
$kcadm create users -s username=joel -s 'attributes.firstName=Joel' -s 'attributes.lastName=Driffin' -s enabled=true -r ${REALM_NAME}
$kcadm set-password --username gyles --new-password gyles -r ${REALM_NAME}
$kcadm set-password --username alad --new-password alad -r ${REALM_NAME}
$kcadm set-password --username joel --new-password joel -r ${REALM_NAME}
$kcadm add-roles --uusername gyles --rolename gate-keeper -r ${REALM_NAME}
$kcadm add-roles --uusername alad --rolename flag-keeper -r ${REALM_NAME}
$kcadm add-roles --uusername joel --rolename warden -r ${REALM_NAME}

KID=$($kcadm get keys -r master | jq -r '.keys[] | select(.algorithm == "RSA-OAEP").kid')
CLIENT_SEC=$($kcadm get clients -r ${REALM_NAME} | jq -r '.[] |  select(.clientId == "'$CLIENT_NAME'").secret')
echo "client-id: $CID"
echo "jwt-key: $KID"
echo "client-secret: $CLIENT_SEC"
echo
echo "Updating client-id in application.yaml"
sed -i -E "s/(\s+client-secret: )\S*/\1${CLIENT_SEC}/" ./02_open-id/src/main/resources/application.yaml
sed -i -E "s/(\s+client-secret: )\S*/\1${CLIENT_SEC}/" ./03_mp-jwt/castle-jwt/src/main/resources/application.yaml
sed -i -E "s/(\s+client-secret: )\S*/\1${CLIENT_SEC}/" ./03_mp-jwt/watchtower-jwt/src/main/resources/application.yaml

$kc export --realm ${REALM_NAME} --file ./keycloak/beginning-helidon.json

echo
while [ true ]; do
  echo "Press Ctrl+C to stop Keycloak and exit"
  read
done


