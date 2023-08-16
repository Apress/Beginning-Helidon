package com.apress.helidon.ch05wizard.client.mprestclient;

import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class WizardExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException toThrowable(Response response) {
        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return new RuntimeException("Server Down");
        } else if (response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
            return new RuntimeException("Server bad response");
        } else {
            return new RuntimeException("Something went terribly wrong: " + response);
        }
    }
}