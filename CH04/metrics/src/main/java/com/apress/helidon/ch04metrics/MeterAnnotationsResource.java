package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.annotation.Metered;

@ApplicationScoped
@Path("/meter/annotations")
public class MeterAnnotationsResource {

    /**
     * This method returns a random integer between 0 and 100.
     */
    @GET
    @Metered(name = "mtr1",
            absolute = true,
            description = "Simple annotation-based meter")
    public Integer meter() {
        return Double.valueOf(Math.random() * 100).intValue();
    }
}
