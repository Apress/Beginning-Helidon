package com.apress.helidon.ch05wizard.client.mprestclient;

import com.apress.helidon.ch05wizard.client.Wizard;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.concurrent.CompletionStage;

@RegisterRestClient(baseUri="http://localhost:8080/wizard")
interface WizardAsyncRestClient {

     @GET
     CompletionStage<Wizard> getMostMightyWizard();

     @Path("/{name}")
     @GET
     CompletionStage<Wizard> getWizardByName(@PathParam("name") String name);
}