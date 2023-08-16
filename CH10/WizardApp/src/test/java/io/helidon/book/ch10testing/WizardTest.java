package io.helidon.book.ch10testing;

import io.helidon.microprofile.tests.junit5.*;

import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@HelidonTest(resetPerTest = true)
class WizardTitleTest {

    @Test
    void testDefaultTitle(WebTarget webTarget) {

        String result = webTarget.path("wizard/title")
                .request()
                .get(String.class);

        assertEquals("The Greatest!", result);
    }

    @Test
    @AddConfig(key = "app.title", value = "The Mighty!")
    void testModifiedTitle(WebTarget webTarget) {

        String result = webTarget.path("wizard/title")
                .request()
                .get(String.class);

        assertEquals("The Mighty!", result);
    }
}