package com.apress.beginninghelidon.jwt.watchtower;

import io.helidon.security.annotations.Authenticated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.URI;

@Path("/castle-lookout")
@RequestScoped
@Authenticated
public class SignalResource {

    private final Client client;
    private final URI secondaryUrl;

    @Inject
    public SignalResource(@ConfigProperty(name = "app.watchtower-url") URI secondaryUrl) {
        this.secondaryUrl = secondaryUrl;
        this.client = ClientBuilder.newBuilder()
                .build();
    }

    @POST
    @Path("/send-signal")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response signalToWatchTower(@QueryParam("msg") String msg) {
        try (Response res = client
                .target(secondaryUrl)
                .path("/signal")
                .request()
                .post(Entity.entity(msg, MediaType.TEXT_PLAIN_TYPE))) {

            return Response.status(res.getStatus()).entity(res.getEntity()).build();
        }
    }

    @GET
    @Path("/watchtower-location")
    public String getWatchtowerLocation(){
        return secondaryUrl.toASCIIString();
    }
}
