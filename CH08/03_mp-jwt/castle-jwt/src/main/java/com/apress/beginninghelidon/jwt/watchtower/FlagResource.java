package com.apress.beginninghelidon.jwt.watchtower;

import io.helidon.security.annotations.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/flag")
@ApplicationScoped
@Authenticated
public class FlagResource {

    @Inject
    private CastleBean castleBean;

    @PUT
    @RolesAllowed({"flag-keeper", "warden"})
    @Path("/raise")
    public Response raiseFlag() {
        if (castleBean.getFlagRaised().compareAndSet(false, true)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

    @PUT
    @RolesAllowed({"flag-keeper", "warden"})
    @Path("/hide")
    public Response hideFlag() {
        if (castleBean.getFlagRaised().compareAndSet(true, false)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }
}
