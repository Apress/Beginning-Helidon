package com.apress.helidon.ch03;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;

@Path("/battle")
@RequestScoped
public class BattleResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private final SorcererBean sorcererBean;
    private final OrcArmyBean orcArmyBean;

    @Inject
    public BattleResource(SorcererBean sorcererBean, OrcArmyBean orcArmyBean) {
        this.sorcererBean = sorcererBean;
        this.orcArmyBean = orcArmyBean;
    }

    @GET
    @Path("/sorcerer")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getSorcerer() {
        return sorcererBean.toJson();
    }

    @GET
    @Path("/orcs")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getOrcs() {
        return JSON.createObjectBuilder()
                .add("leader", orcArmyBean.getLeader())
                .add("healer", orcArmyBean.getHealer())
                .build();
    }
}
