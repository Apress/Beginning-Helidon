
package io.helidon.book.ch10testing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;

@ApplicationScoped
public class WizardProvider {

    private HashMap<String, Wizard> wizards;

    private String title;

    @Inject
    public WizardProvider(@ConfigProperty(name = "app.title") String title) {
        wizards = new HashMap<>();
        Wizard oz = new Wizard();
        oz.setName("Oz");
        wizards.put("Oz",oz);
        Wizard skylar = new Wizard();
        skylar.setName("Skylar");
        wizards.put("Skylar", skylar);

        this.title = title;
    }

    public Wizard getMostMightyWizard() {
        return wizards.get("Oz");
    }
    public Wizard getWizardByName(String name) {
        return wizards.get(name);
    }

    public String getTitle() {
        return title;
    }

}
