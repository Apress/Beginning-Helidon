package com.apress.beginninghelidon.oidc;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.helidon.microprofile.server.Server;
import io.helidon.security.SecurityContext;
import io.helidon.security.annotations.Authenticated;


@Path("/castle")
@ApplicationScoped
@Authenticated
public class CastleResource {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    private final AtomicBoolean gateOpened = new AtomicBoolean(false);
    private final AtomicBoolean flagRaised = new AtomicBoolean(false);

    public static void main(String[] args) {
        Server.create().start();
    }

    @GET
    @Path("/login")
    public Response login(@QueryParam("redirect") URI redirect) {
        return Response
                .seeOther(redirect)
                .build();
    }

    @GET
    @Path("/role/${roleName}")
    public Response login(@Context SecurityContext securityContext,
                          @PathParam("roleName") String roleName) {
        return Response
                .ok(securityContext.isUserInRole(roleName))
                .build();
    }

    @GET
    @Path("/principal")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getPrincipal(@Context SecurityContext secCtx) {
        return JSON.createObjectBuilder()
                .add("user", Optional.ofNullable(secCtx.userName()).orElse(""))
                .add("fullName", secCtx.user()
                .flatMap(s -> s.principal().abacAttribute("full_name"))
                .map(String::valueOf)
                .orElse(""))
                .build();
    }

    @GET
    @RolesAllowed({"gate-keeper", "flag-keeper", "warden"})
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject castleStatus() {
        return JSON.createObjectBuilder()
                .add("gateOpened", gateOpened.get())
                .add("flagRaised", flagRaised.get())
                .build();
    }

    @PUT
    @RolesAllowed({"gate-keeper", "warden"})
    @Path("/gate/open")
    public Response openGate() {
        if (gateOpened.get()) {
            return Response.notModified().build();
        } else {
            gateOpened.set(true);
            return Response.ok().build();
        }
    }

    @PUT
    @RolesAllowed({"flag-keeper", "warden"})
    @Path("/flag/raise")
    public Response raiseFlag() {
        if (flagRaised.get()) {
            return Response.notModified().build();
        } else {
            flagRaised.set(true);
            return Response.ok().build();
        }
    }

    @PUT
    @RolesAllowed({"gate-keeper", "warden"})
    @Path("/gate/close")
    public Response closeGate() {
        if (gateOpened.get()) {
            gateOpened.set(false);
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

    @PUT
    @RolesAllowed({"flag-keeper", "warden"})
    @Path("/flag/hide")
    public Response hideFlag() {
        if (flagRaised.get()) {
            flagRaised.set(false);
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

}
