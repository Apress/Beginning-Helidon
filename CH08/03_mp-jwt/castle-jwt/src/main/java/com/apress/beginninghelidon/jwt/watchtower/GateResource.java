package com.apress.beginninghelidon.jwt.watchtower;

import io.helidon.security.annotations.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/gate")
@ApplicationScoped
@Authenticated
public class GateResource {

    @Inject
    private CastleBean castleBean;

    @PUT
    @RolesAllowed({"gate-keeper", "warden"})
    @Path("/open")
    public Response openGate() {
        if (castleBean.getGateOpened().compareAndSet(false, true)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

    @PUT
    @RolesAllowed({"gate-keeper", "warden"})
    @Path("/close")
    public Response closeGate() {
        if (castleBean.getGateOpened().compareAndSet(true, false)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }
}
