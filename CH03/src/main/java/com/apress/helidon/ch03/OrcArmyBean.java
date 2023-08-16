package com.apress.helidon.ch03;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class OrcArmyBean {

    private final Orc leader;
    private final JsonObject healer;

    @Inject
    public OrcArmyBean(
            // Uses automatic converter
            @ConfigProperty(name = "orc.leader") Orc leader,
            // Uses JsonConverter
            @ConfigProperty(name = "orc.healer") JsonObject healer
    ) {
        this.leader = leader;
        this.healer = healer;
    }

    public JsonObject getLeader() {
        return leader.toJson();
    }

    public JsonObject getHealer(){
        return healer;
    }
}
