package com.apress.helidon.ch04metrics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;

import java.util.Random;

@ApplicationScoped
@Path("/simpletimer/annotations")
public class SimpleTimerAnnotationsResource {
    private Random random = new Random();

    /**
     * This method does nothing, but sleeps random number of seconds in [0-5) interval.
     */
    @GET
    @SimplyTimed(name="stmr1",
            absolute=true,
            description = "Simple timer using annotations")
    public void simpleTimer() throws InterruptedException {
        Thread.sleep(random.nextInt(5000));
    }
}
