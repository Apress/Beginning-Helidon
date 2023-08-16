package com.apress.beginninghelidon.jwt.watchtower;

import io.helidon.security.SecurityContext;
import io.helidon.security.annotations.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@Path("/castle")
@ApplicationScoped
@Authenticated
public class CastleResource {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Inject
    private CastleBean castleBean;

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
                .add("gateOpened", castleBean.getGateOpened().get())
                .add("flagRaised", castleBean.getFlagRaised().get())
                .build();
    }
}