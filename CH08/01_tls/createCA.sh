#!/bin/bash

openssl req -x509 -sha256 \
-days 1825 \
-newkey rsa:4096 \
-passout pass:'password' \
-subj "/C=CZ/ST=Prague/L=Prague/O=Apress/OU=BeginningHelidon/CN=beginning-helidon.apress.com" \
-keyout rootCA.key \
-out rootCA.crt

