
package com.apress.helidon.ch05wizards;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;


@Path("/wizard")
@RequestScoped
public class WizardResource {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private final WizardProvider wizardProvider;

    @Inject
    public WizardResource(WizardProvider wizardProvider) {
        this.wizardProvider = wizardProvider;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard getWizard() {
        return wizardProvider.getWizard();
    }

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard getWizard(@PathParam("name") String name) {
        return wizardProvider.getWizardByName(name);
    }


    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addWizard(JsonObject jsonObject) {
        if (!jsonObject.containsKey("name")) {
            JsonObject entity = JSON.createObjectBuilder()
                    .add("error", "No name provided")
                    .build();
            return Response.status(Response.Status.UNPROCESABLE_ENTITY).entity(entity).build();
        }

        String name = jsonObject.getString("name");
        Wizard wizard = new Wizard();
        wizard.setName(name);
        wizardProvider.addWizard(wizard);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
