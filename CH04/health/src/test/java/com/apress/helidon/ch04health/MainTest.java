package com.apress.helidon.ch04health;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
public class MainTest {

    @Inject
    private WebTarget target;

    @Test
    void testHealth() {
        Response response = target
                .path("health/ready")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    void testMetrics() {
        Response response = target
                .path("metrics")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    void testGreet() {
        JsonObject jsonObject = target
                .path("greet")
                .request()
                .get(JsonObject.class);
        assertThat(jsonObject.getString("message"), is("Hello World!"));
    }
                
    @Test
    void testGreetings() {
        JsonObject jsonObject = target
                .path("greet/Dmitry")
                .request()
                .get(JsonObject.class);
        assertThat(jsonObject.getString("message"), is("Hello Dmitry!"));

        try (Response r = target
                .path("greet/greeting")
                .request()
                .put(Entity.entity("{\"greeting\" : \"Hola\"}", MediaType.APPLICATION_JSON))) {
            assertThat(r.getStatus(), is(204));
        }

        jsonObject = target
                .path("greet/Dmitry")
                .request()
                .get(JsonObject.class);
        assertThat(jsonObject.getString("message"), is("Hola Dmitry!"));

        try (Response r = target
                .path("greet/greeting")
                .request()
                .put(Entity.entity("{\"greeting\" : \"Hello\"}", MediaType.APPLICATION_JSON))) {
            assertThat(r.getStatus(), is(204));
        }
    }
                
}
