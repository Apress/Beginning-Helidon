package com.apress.helidon.ch05wizard.client;

import com.apress.helidon.ch05wizard.client.jaxrs.LoggerFilter;
import com.apress.helidon.ch05wizard.client.mprestclient.WizardRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Path("/wizardClient")
public class SorceryMinistryResource {

    @Inject
    @RestClient
    private WizardRestClient restClient;

    private WebTarget client = ClientBuilder.newClient()
            .register(new LoggerFilter())
            .target("http://localhost:8080");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard getMostMightyWizard() {
        return restClient.getMostMightyWizard();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard getWizardByName(@PathParam("name") String name) {
        return restClient.getWizardByName(name);
    }

    @GET
    @Path("/jaxrs")
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard getWizardFromJaxrsClient() {
        return client
                .path("/wizard")
                .request(MediaType.APPLICATION_JSON)
                .get(Wizard.class);
    }

    @GET
    @Path("/jaxrs/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard addWizardAndCheck() {
        Wizard wizard = new Wizard();
        wizard.setName("NewWizard");

        client.path("/wizard/add")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(wizard, MediaType.APPLICATION_JSON));

        return client
                .path("/wizard/NewWizard")
                .request(MediaType.APPLICATION_JSON)
                .get(Wizard.class);
    }
}
