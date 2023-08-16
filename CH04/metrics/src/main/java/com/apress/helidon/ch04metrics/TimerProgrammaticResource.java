package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.Timer;

import java.util.Random;
import java.util.concurrent.Callable;

@ApplicationScoped
@Path("/timer/programmatic")
public class TimerProgrammaticResource {
    private Random random = new Random();

    private Timer timer;

    @Inject
    public TimerProgrammaticResource(MetricRegistry metricRegistry) {
        timer = metricRegistry.timer("tmr2");
    }

    /**
     * This method does nothing, but sleep for a random period not longer than 5 seconds.
     */
    @GET
    public Integer timer() throws Exception {
        Callable<Integer> callable = () -> {
            int sleepSec = random.nextInt(5000);
            Thread.sleep(sleepSec);
            return sleepSec;
        };

        return timer.time(callable);
    }
}
