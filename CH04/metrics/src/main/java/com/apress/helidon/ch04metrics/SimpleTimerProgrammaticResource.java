package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.SimpleTimer;

import java.util.Random;

@ApplicationScoped
@Path("/simpletimer/programmatic")
public class SimpleTimerProgrammaticResource {
    private Random random = new Random();

    private SimpleTimer simpleTimer;

    @Inject
    public SimpleTimerProgrammaticResource(MetricRegistry metricRegistry) {
        createSimpleTimer(metricRegistry);
    }

    /**
     * This method does nothing, but sleep for a random period not longer than 5 seconds.
     */
    @GET
    public void simpleTimer() {
        Runnable runnable = () -> {
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        simpleTimer.time(runnable);
    }

    private void createSimpleTimer(MetricRegistry metricRegistry) {
        Metadata metadata = Metadata.builder()
                .withName("stmr2")
                .withDescription("Simple timer programmatic")
                .withType(MetricType.SIMPLE_TIMER)
                .build();

        simpleTimer = metricRegistry.simpleTimer(metadata);
    }
}
