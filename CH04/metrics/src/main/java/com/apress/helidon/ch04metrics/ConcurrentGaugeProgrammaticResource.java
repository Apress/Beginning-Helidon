package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.ConcurrentGauge;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.Tag;

@ApplicationScoped
@Path("/concurrentgauge/programmatic")
public class ConcurrentGaugeProgrammaticResource {

    private ConcurrentGauge concurrentGauge;

    @Inject
    public ConcurrentGaugeProgrammaticResource(MetricRegistry metricRegistry) {
        concurrentGauge = metricRegistry.concurrentGauge("cgauge2", new Tag("method", "programmatic"));
    }

    @GET
    public void concurrentGauge() {
        concurrentGauge.inc();
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            concurrentGauge.dec();
        }
    }
}
