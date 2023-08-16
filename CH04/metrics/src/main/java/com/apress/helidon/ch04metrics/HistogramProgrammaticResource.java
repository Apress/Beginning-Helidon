package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;

@ApplicationScoped
@Path("/histogram")
public class HistogramProgrammaticResource {

    private Histogram histogram;

    @Inject
    public HistogramProgrammaticResource(MetricRegistry metricRegistry) {
        createHistogram(metricRegistry);
    }

    /**
     * Updates histogram with the passed value.
     */
    @GET
    @Path("/{value}")
    public void histogram(@PathParam("value") Long value) {
        histogram.update(value);
    }

    private void createHistogram(MetricRegistry metricRegistry) {
        Metadata metadata = Metadata.builder()
                .withName("histogram")
                .withDescription("Histogram programmatic")
                .withType(MetricType.HISTOGRAM)
                .build();

        histogram = metricRegistry.histogram(metadata);
    }
}
