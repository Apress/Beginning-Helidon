package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.metrics.MetricRegistry;

import java.util.Random;

@ApplicationScoped
public class GaugeProgrammatic {

    private Random random = new Random();

    @Inject
    public GaugeProgrammatic(MetricRegistry metricRegistry) {
        metricRegistry.gauge("gauge2", this::measurement);
    }

    private void initObserver(@Observes @Initialized(ApplicationScoped.class) final Object ignoredEvent) {
    }

    public Integer measurement() {
        return random.nextInt(100);
    }

}
