package com.apress.beginninghelidon.jwt.watchtower;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationScoped
public class CastleApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
                CastleResource.class,
                FlagResource.class,
                GateResource.class,
                SignalResource.class
        );
    }
}
