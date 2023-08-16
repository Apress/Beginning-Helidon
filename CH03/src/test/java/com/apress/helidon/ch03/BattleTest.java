package com.apress.helidon.ch03;

import java.io.StringWriter;
import java.util.Map;

import io.helidon.microprofile.tests.junit5.HelidonTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.client.WebTarget;

@HelidonTest
public class BattleTest {

    static {
        // Notice how configuration from system properties take precedence over config file
        System.setProperty("sorcerer.orcSlayingPotions", "5");
    }

    @Test
    void sorcererPropertiesTest(WebTarget webTarget) {
        JsonObject entity = webTarget.path("/battle/sorcerer")
                .request()
                .get()
                .readEntity(JsonObject.class);

        StringWriter writer = new StringWriter();
        Json.createWriterFactory(Map.of(JsonGenerator.PRETTY_PRINTING, true))
                .createWriter(writer)
                .write(entity);

        String expected = """
                {
                    "name": "Merlin",
                    "title": "Merlin_30",
                    "level": 30,
                    "orcSlayingPotions": 5,
                    "invisibilityCloak": true,
                    "weaponsList": "[axe, sword, wand]",
                    "weaponsArray": "[axe, sword, wand]",
                    "weaponsSet": "[axe, sword, wand]"
                }""";

        String actual = writer.toString();

        assertEquals(expected, actual);
    }
}
