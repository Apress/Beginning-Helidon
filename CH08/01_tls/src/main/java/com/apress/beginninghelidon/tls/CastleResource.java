package com.apress.beginninghelidon.tls;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.helidon.microprofile.server.Server;
import io.helidon.security.annotations.Authenticated;


@Path("/castle")
@ApplicationScoped
@Authenticated
public class CastleResource {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private final AtomicBoolean gateOpened = new AtomicBoolean(false);

    public static void main(String[] args) {
        Server.create().start();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public JsonObject getCastleStatus() {
        return JSON.createObjectBuilder()
                .add("gate", gateOpened.get())
                .build();
    }

    @PUT
    @Path("/gate/open")
    @RolesAllowed({"gate-keeper", "warden"})
    public Response openGate() {
        if (gateOpened.get()) {
            return Response.notModified().build();
        } else {
            gateOpened.set(true);
            return Response.ok().build();
        }
    }

    @PUT
    @Path("/gate/close")
    @RolesAllowed({"gate-keeper", "warden"})
    public Response closeGate() {
        if (gateOpened.get()) {
            gateOpened.set(false);
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

}
