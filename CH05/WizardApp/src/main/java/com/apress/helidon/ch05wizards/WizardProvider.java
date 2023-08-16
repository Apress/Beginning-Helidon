
package com.apress.helidon.ch05wizards;

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

    public Wizard getWizardByName(String name){
        return wizards.get(name);
    }

    public void addWizard(Wizard wizard){
        wizards.put(wizard.getName(), wizard);
    }
}
