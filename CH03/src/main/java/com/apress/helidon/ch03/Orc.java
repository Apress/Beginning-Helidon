package com.apress.helidon.ch03;

import java.io.StringReader;
import java.util.Collections;

import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;

public class Orc {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    private final String name;
    private final int level;

    public Orc(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    /**
     * Goblin can convert itself from JSON.
     * <p/>
     * Example: {"name": "Corgoth", "level": "32"}
     * <p/>
     * Automatic converter looks for methods:
     * <ul>
     * <li>public static Orc of(String val)</li>
     * <li>public static Orc valueOf(String val)</li>
     * <li>public static Orc parse(CharSequence val)</li>
     * </ul>
     *
     * @param configValue config string value
     * @return parsed Orc object
     */
    public static Orc of(String configValue) {
        JsonObject jsonObject = Json.createReader(new StringReader(configValue)).readObject();
        return new Orc(jsonObject.getString("name"), jsonObject.getInt("level"));
    }

    public JsonObject toJson() {
        return JSON.createObjectBuilder()
                .add("name", name)
                .add("level", level)
                .build();
    }
}
