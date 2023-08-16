package com.apress.helidon.ch03;

import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class SorcererBean {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private final String name;
    private final String title;
    private final int level;
    private final int orcSlayingPotions;
    private final boolean invisibilityCloak;
    private final String[] weaponsArray;
    private final List<String> weaponsList;
    private final Set<String> weaponsSet;
    private final SorcererProperties sorcererProperties;

    @Inject
    public SorcererBean(
            @ConfigProperty(name = "sorcerer.name") String name,
            @ConfigProperty(name = "sorcerer.title") String title,
            @ConfigProperty(name = "sorcerer.level") int level,
            @ConfigProperty(name = "sorcerer.orcSlayingPotions", defaultValue = "0") int orcSlayingPotions,
            @ConfigProperty(name = "sorcerer.invisibilityCloak") boolean invisibilityCloak,
            @ConfigProperty(name = "sorcerer.weapons") String[] weaponsArray,
            @ConfigProperty(name = "sorcerer.weapons") List<String> weaponsList,
            @ConfigProperty(name = "sorcerer.weapons") Set<String> weaponsSet,
            @ConfigProperties SorcererProperties sorcererProperties
    ) {
        this.name = name;
        this.title = title;
        this.level = level;
        this.orcSlayingPotions = orcSlayingPotions;
        this.invisibilityCloak = invisibilityCloak;
        this.weaponsArray = weaponsArray;
        this.weaponsList = weaponsList;
        this.weaponsSet = weaponsSet;
        this.sorcererProperties = sorcererProperties;
    }

    public JsonObject toJson(){
        return JSON.createObjectBuilder()
                .add("name", name)
                .add("title", title)
                .add("level", level)
                .add("orcSlayingPotions", orcSlayingPotions)
                .add("invisibilityCloak", invisibilityCloak)
                .add("weaponsList", weaponsList.toString())
                .add("weaponsArray", Arrays.toString(sorcererProperties.weapons))
                .add("weaponsSet", weaponsSet.toString())
                .build();
    }
}