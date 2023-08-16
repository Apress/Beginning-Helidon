package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.Meter;
import org.eclipse.microprofile.metrics.MetricRegistry;

@ApplicationScoped
@Path("/meter/programmatic")
public class MeterProgrammaticResource {

    private Meter meter;

    @Inject
    public MeterProgrammaticResource(MetricRegistry metricRegistry) {
        meter = metricRegistry.meter("mtr2");
    }

    @GET
    public double meterProgrammatic() {
        meter.mark();
        return meter.getMeanRate();
    }
}
