package com.apress.helidon.ch05wizard.client.mprestclient;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;

import java.util.logging.Logger;

public class WizardRequestFilter implements ClientRequestFilter {

    private Logger log = Logger.getLogger(String.valueOf(WizardRequestFilter.class));

    @Override
    public void filter(ClientRequestContext requestContext) {
        log.info("Request intercepted: " + requestContext.getHeaders());
    }
}
