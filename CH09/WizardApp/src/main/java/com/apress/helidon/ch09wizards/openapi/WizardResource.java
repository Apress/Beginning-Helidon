
package com.apress.helidon.ch09wizards.openapi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/wizard")
@RequestScoped
public class WizardResource {

    private final WizardProvider wizardProvider;

    @Inject
    public WizardResource(WizardProvider wizardProvider) {
        this.wizardProvider = wizardProvider;
    }


    @GET
    @Operation(summary = "Returns Wizard Information", // <1>
            description = "General Wizard Name and Licence information ")
    @APIResponse(description = "Simple JSON containing Wizard name and licence information", // <2>
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Wizard.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public Wizard getWizard() {
        return wizardProvider.getWizard();
    }
}
