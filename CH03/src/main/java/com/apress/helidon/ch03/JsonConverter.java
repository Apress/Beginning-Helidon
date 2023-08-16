package com.apress.helidon.ch03;

import jakarta.annotation.Priority;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReaderFactory;

import java.io.StringReader;
import java.util.Collections;

import org.eclipse.microprofile.config.spi.Converter;

@Priority(101)
public class JsonConverter implements Converter<JsonObject> {
    private static final JsonReaderFactory JSON = Json.createReaderFactory
            (Collections.emptyMap());
    @Override
    public JsonObject convert(String value) {
        return JSON.createReader(new StringReader(value)).readObject();
    }
}