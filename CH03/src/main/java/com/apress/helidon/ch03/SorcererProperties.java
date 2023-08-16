package com.apress.helidon.ch03;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@ConfigProperties(prefix="sorcerer")
public class SorcererProperties {
    public @ConfigProperty(name="orcSlayingPotions") int potions;
    public boolean cloak;
    public String[] weapons;
}
