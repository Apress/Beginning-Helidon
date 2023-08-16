package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.Random;

@ApplicationScoped
@Path("/timer/annotations")
public class TimerAnnotationsResource {
    private Random random = new Random();

    /**
     * This method does nothing, but sleeps random number of seconds in [0-5) interval.
     */
    @GET
    @Timed(name="tmr1",
            absolute=true,
            description = "Timer using annotations")
    public void timer() throws InterruptedException {
        Thread.sleep(random.nextInt(5000));
    }
}
