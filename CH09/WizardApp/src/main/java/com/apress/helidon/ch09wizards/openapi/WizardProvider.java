
package com.apress.helidon.ch09wizards.openapi;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;

@ApplicationScoped
public class WizardProvider {

    private HashMap<String, Wizard> wizards;

    public WizardProvider() {
        wizards = new HashMap<>();
        Wizard oz = new Wizard();
        oz.setName("Oz");
        wizards.put("Oz",oz);
        Wizard skylar = new Wizard();
        skylar.setName("Skylar");
        wizards.put("Skylar",skylar);
    }

    public Wizard getWizard() {
        return wizards.get("Oz");
    }

}
