package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;

@ApplicationScoped
@Path("/concurrentgauge/annotations")
public class ConcurrentGaugeAnnotationsResource {

    /**
     * This method does nothing, but sleep for 20 seconds.
     */
    @GET
    @ConcurrentGauge(name="cgauge1",
            absolute=true,
            description = "Simple annotation-based concurrent gauge")
    public void concurrentGauge() throws InterruptedException {
        Thread.sleep(20 * 1000);
    }
}
