
package io.helidon.pigeonpost;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.atomic.AtomicLong;

import io.helidon.common.reactive.Multi;
import io.helidon.common.reactive.Single;

import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.streams.operators.ProcessorBuilder;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.eclipse.microprofile.reactive.streams.operators.SubscriberBuilder;
import org.junit.jupiter.api.Test;
import org.reactivestreams.FlowAdapters;
import org.reactivestreams.Publisher;

//@HelidonTest
class MainTest {

    @Inject
    @Channel("test")
    Emitter emitter;

    //    @Inject
    //    private WebTarget target;

    @Test
    void testMicroprofileMetrics() {
        Message<String> msg =
                Message.of("payload",
                           () -> CompletableFuture
                                   .completedFuture("Acked!")
                                   .thenAccept(System.out::println),
                           t -> CompletableFuture
                                   .completedFuture("Not acked! Error " + t.getMessage())
                                   .thenAccept(System.out::println));

        msg.nack(new Exception("BOOM!"));

        emitter.send(Message.of(""));

        //        ReactiveStreams
        //                .of(1, 2, 3, 4)
        //                .map(i -> i * 10)
        //                .filter(i -> i < 35)
        //                .forEach(i -> log("Got " + i))
        //                .run();

        ReactiveStreams
                .of(1, 2, 3, 4, 5)
                .filter(i -> i < 5)
                .filter(i -> i > 2)
                .map(i -> i * 10)
                .map(i -> i - 5)
                .map(i -> i * 10)
                .map(i -> i - 5)
                .map(String::valueOf)
                .forEach(i -> log("Got " + i))
                .run();

        var publisherGraph =
                ReactiveStreams.of(1, 2, 3, 4, 5)
                        .filter(i -> i < 5)
                        .filter(i -> i > 2);

        var processor1Graph =
                ReactiveStreams.<Integer>builder()
                        .map(i -> i * 10)
                        .map(i -> i - 5);

        var processor2Graph =
                ReactiveStreams.<Integer>builder()
                        .map(String::valueOf);

        var subscriberGraph =
                ReactiveStreams.<String>builder()
                        .forEach(i -> log("Got " + i));

        publisherGraph
                .via(processor1Graph)
                .via(processor1Graph)
                .via(processor2Graph)
                .to(subscriberGraph)
                .run()
                .toCompletableFuture()
                .join();
    }

    private void log(String msg) {
        System.out.println(msg);
    }

    @Test
    void name() {

        CompletionStage<Void> csWithNull =
                CompletableFuture.completedStage(null);

        Single.create(csWithNull, true)
                .peek(unused -> log("Not invoked"))
                .onComplete(() -> log("Completed!"))
                .ignoreElement()
                .await();


    }

}
