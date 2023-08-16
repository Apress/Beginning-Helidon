#!/bin/bash

openssl genrsa -des3 -passout pass:'password' \
-out server-private.key 4096

openssl req -key server-private.key \
-passin pass:'password' \
-subj "/C=CZ/ST=Prague/L=Prague/O=Apress/OU=BeginningHelidon/CN=castle.beginning-helidon.apress.com" \
-new -out server.csr