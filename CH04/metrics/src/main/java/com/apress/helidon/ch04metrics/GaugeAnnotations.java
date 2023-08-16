package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;

import java.util.Random;

@ApplicationScoped
public class GaugeAnnotations {
    private Random random = new Random();

    @Gauge(name = "gauge1",
            absolute = true,
            description = "Simple annotation-based gauge",
            unit = MetricUnits.NONE)
    public Integer measurement() {
        return random.nextInt(100);
    }
}
