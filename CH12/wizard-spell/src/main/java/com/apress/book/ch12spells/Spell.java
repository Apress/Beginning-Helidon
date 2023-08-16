package com.apress.book.ch12spells;

import java.io.Serializable;

public class Spell implements Serializable {

    private String wizardName;

    private String spell;

    public Spell() {
    }

    public Spell(String wizardName, String spell) {
        this.wizardName = wizardName;
        this.spell = spell;
    }

    public String getWizardName() {
        return wizardName;
    }

    public void setWizardName(String wizardName) {
        this.wizardName = wizardName;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
