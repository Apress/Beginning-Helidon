package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metric;

@ApplicationScoped
@Path("/counter/annotations")
public class CounterAnnotationsResource {

    @Inject
    @Metric(name="cntr1",
            absolute=true)
    Counter counter;

    @GET
    @Counted(name="cntr1",
            absolute=true,
            description = "Simple annotation-based counter")
    public Long count() {
        return counter.getCount();
    }
}
