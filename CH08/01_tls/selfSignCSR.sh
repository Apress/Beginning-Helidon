#!/bin/bash

cat <<EOT >> server509.ext
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
subjectAltName = @alt_names
[alt_names]
DNS.1 = localhost
DNS.2 = castle.beginning-helidon.apress.com
EOT

openssl x509 -req -CA rootCA.crt \
-CAkey rootCA.key \
-in server.csr \
-out server.crt \
-days 1825 \
-CAcreateserial \
-passin pass:'password' \
-extfile server509.ext

openssl pkcs12 -inkey server-private.key \
-in server.crt -export \
-passin pass:'password' \
-passout pass:'password' \
-out server.p12