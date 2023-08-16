package com.apress.helidon.ch05wizard.client.mprestclient;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;

import java.util.logging.Logger;

public class WizardResponseFilter implements ClientResponseFilter {

    private Logger log = Logger.getLogger(String.valueOf(WizardResponseFilter.class));

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
        log.info("Intercepted response status" + responseContext.getHeaders());
    }
}