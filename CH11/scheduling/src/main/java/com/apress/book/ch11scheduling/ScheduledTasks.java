package com.apress.book.ch11scheduling;

import io.helidon.microprofile.scheduling.FixedRate;
import io.helidon.microprofile.scheduling.Scheduled;
import io.helidon.scheduling.CronInvocation;
import io.helidon.scheduling.FixedRateInvocation;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class ScheduledTasks {

    @FixedRate(initialDelay = 5, value = 10, timeUnit = TimeUnit.SECONDS)
    public void simpleFixedRate() {
        System.out.println("Every 10 seconds, first invocation 5 seconds after start");
    }

    @FixedRate(initialDelay = 5, value = 10, timeUnit = TimeUnit.SECONDS)
    public void configuredFixedRate() {
        System.out.println("Overridden config fix task ");
    }

    @FixedRate(initialDelay = 10, value = 5, timeUnit = TimeUnit.SECONDS)
    public void simpleFixedRateInvokeData(FixedRateInvocation inv) {
        System.out.println("Method invoked " + inv.description());
    }

    @Scheduled(value = "0 0 7 ? * *", concurrentExecution = false)
    public void morningRun() {
        System.out.println("Run every day at 7 AM");
    }

    @Scheduled(value = "0 0 7 ? * *", concurrentExecution = false)
    public void morningRunWithInvokeData(CronInvocation inv) {
        System.out.println("Method invoked " + inv.description());
    }
}
