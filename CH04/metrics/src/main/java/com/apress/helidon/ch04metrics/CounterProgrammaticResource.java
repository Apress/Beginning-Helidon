package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.*;

@ApplicationScoped
@Path("/counter/programmatic")
public class CounterProgrammaticResource {
    private Counter counter;

    @Inject
    public CounterProgrammaticResource(MetricRegistry metricRegistry) {
        createCounter(metricRegistry);
    }

    @GET
    public Long counterProgrammatic() {
        counter.inc();
        return counter.getCount();
    }

    private void createCounter(MetricRegistry metricRegistry) {
        Tag tag = new Tag("method", "programmatic");
        Metadata metadata = Metadata.builder()
                .withName("cntr2")
                .withDescription("Simple programmatic counter")
                .withType(MetricType.COUNTER)
                .withUnit(MetricUnits.NONE)
                .build();

        counter = metricRegistry.counter(metadata, tag);
    }
}
