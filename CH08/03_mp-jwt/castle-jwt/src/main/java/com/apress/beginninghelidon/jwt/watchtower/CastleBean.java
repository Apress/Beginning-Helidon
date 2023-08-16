package com.apress.beginninghelidon.jwt.watchtower;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class CastleBean {
    private final AtomicBoolean flagRaised = new AtomicBoolean(false);
    private final AtomicBoolean gateOpened = new AtomicBoolean(false);

    public AtomicBoolean getFlagRaised() {
        return flagRaised;
    }

    public AtomicBoolean getGateOpened() {
        return gateOpened;
    }
}
