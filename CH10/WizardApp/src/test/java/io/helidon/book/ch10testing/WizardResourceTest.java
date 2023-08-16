package io.helidon.book.ch10testing;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@HelidonTest
public class WizardResourceTest {

    @Inject
    private WebTarget webTarget;

    @Test
    void testWizard() {
        JsonObject jsonObject = webTarget.path("/wizard")
                .request()
                .get(JsonObject.class);

        validateWizard(jsonObject, "Oz");
    }

    @Test
    void testWizardByName() {
        JsonObject jsonObject = webTarget.path("/wizard/Skylar")
                .request()
                .get(JsonObject.class);

        validateWizard(jsonObject, "Skylar");
    }

    private void validateWizard(JsonObject jsonObject, String nameExpected){
        String actual = jsonObject.getString("name");
        assertEquals(nameExpected, actual, nameExpected + " is expected");
    }
}
