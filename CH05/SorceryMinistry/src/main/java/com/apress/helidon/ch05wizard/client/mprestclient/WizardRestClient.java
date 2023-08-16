package com.apress.helidon.ch05wizard.client.mprestclient;

import com.apress.helidon.ch05wizard.client.Wizard;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri="http://localhost:8080/wizard")
@RegisterProvider(WizardExceptionMapper.class)
@RegisterProvider(WizardRequestFilter.class)
@RegisterProvider(WizardResponseFilter.class)
@RegisterClientHeaders(WizardHeaderHandler.class)
public
interface WizardRestClient {

     @GET
     Wizard getMostMightyWizard();

     @Path("/{name}")
     @GET
     Wizard getWizardByName(@PathParam("name") String name);
}