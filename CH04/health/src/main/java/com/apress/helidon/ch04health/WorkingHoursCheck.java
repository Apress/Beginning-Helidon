package com.apress.helidon.ch04health;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import java.time.LocalDateTime;

/**
 * Custom liveness health check returning UP only during working hours.
 */
@Liveness
@ApplicationScoped
public class WorkingHoursCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .name("working-hours-check")
                .withData("time", LocalDateTime.now().toString())
                .status(getStatus())
                .build();
    }

    private boolean getStatus() {
        int hour = LocalDateTime.now().getHour();
        return hour >= 9 && hour <= 17;
    }
}
