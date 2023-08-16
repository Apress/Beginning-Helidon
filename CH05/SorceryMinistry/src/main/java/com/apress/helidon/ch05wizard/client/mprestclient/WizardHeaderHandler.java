package com.apress.helidon.ch05wizard.client.mprestclient;

import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import java.util.Collections;

public class WizardHeaderHandler implements ClientHeadersFactory {

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
                                                 MultivaluedMap<String, String> clientOutgoingHeaders) {

        return new MultivaluedHashMap<>() {{
            put("Magic-Header", Collections.singletonList("Custom header magic value"));
        }};
    }
}