package com.apress.helidon.ch05wizard.client.jaxrs;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;

import java.util.logging.Logger;

public class LoggerFilter implements ClientRequestFilter, ClientResponseFilter {

    Logger logger = Logger.getLogger(Logger.class.getCanonicalName());

    @Override
    public void filter(ClientRequestContext requestContext) {
        logger.info(requestContext.getHeaders().toString());
    }

    @Override
    public void filter(ClientRequestContext requestContext,
                       ClientResponseContext responseContext) {
        logger.info(responseContext.getHeaders().toString());
    }
}
