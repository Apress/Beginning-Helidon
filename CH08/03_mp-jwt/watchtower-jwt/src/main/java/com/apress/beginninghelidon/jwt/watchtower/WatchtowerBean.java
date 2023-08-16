package com.apress.beginninghelidon.jwt.watchtower;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;

import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class WatchtowerBean {

    private final AtomicReference<SseBroadcaster> sseBroadcaster = new AtomicReference<>();
    private volatile Sse sse;

    public void blipWatchtowerLights(){
        SseBroadcaster broadcaster = sseBroadcaster.get();
        if(broadcaster != null){
            broadcaster.broadcast(sse.newEvent("blip"));
        }
    }

    public void registerCastleLookout(SseEventSink eventSink, Sse sse) {
        this.sse = sse;
        sseBroadcaster
                .updateAndGet(old -> old == null ? sse.newBroadcaster() : old)
                .register(eventSink);
    }
}
