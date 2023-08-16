
package io.helidon.book.ch10testing;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/wizard")
@RequestScoped
public class WizardResource {

    private final WizardProvider wizardProvider;

    @Inject
    public WizardResource(WizardProvider wizardProvider) {
        this.wizardProvider = wizardProvider;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard getWizard() {
        return wizardProvider.getMostMightyWizard();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard getWizardByName(@PathParam("name") String name) {
        return wizardProvider.getWizardByName(name);
    }

    @GET
    @Path("title")
    public String getTitle() {return wizardProvider.getTitle();}
}
