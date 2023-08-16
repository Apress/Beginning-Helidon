package com.apress.beginninghelidon.jwt.watchtower;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/watchtower")
@ApplicationScoped
public class WatchtowerResource {

    private final WatchtowerBean watchtowerBean;
    private final JsonWebToken jwt;

    @Inject
    public WatchtowerResource(WatchtowerBean watchtowerBean, JsonWebToken jwt) {
        this.watchtowerBean = watchtowerBean;
        this.jwt = jwt;
    }

    @POST
    @Path("/signal")
    @RolesAllowed({"warden"})
    public void signal(@Context SecurityContext securityContext, String msg) {
        String user = securityContext.getUserPrincipal().getName();
        jwt.getGroups().forEach(s -> {
            if (securityContext.isUserInRole(s))
                System.out.println(user + " is in role " + s);
        });
        System.out.println("Watchtower signalled by " + user + " from the castle! Message is " + msg);
        watchtowerBean.blipWatchtowerLights();
    }

    @GET
    @Path("register")
    @PermitAll
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void castleLookout(@Context SseEventSink eventSink, @Context Sse sse) {
        watchtowerBean.registerCastleLookout(eventSink, sse);
    }
}