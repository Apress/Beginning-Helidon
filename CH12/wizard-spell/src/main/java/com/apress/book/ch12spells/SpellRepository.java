package com.apress.book.ch12spells;

import com.oracle.coherence.repository.AbstractRepository;

import com.tangosol.net.NamedMap;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;

@ApplicationScoped
public class SpellRepository extends AbstractRepository<String, Spell> {
    @Inject
    private NamedMap<String, Spell> spells;

    protected NamedMap<String, Spell> getMap() {
        return spells;
    }

    protected String getId(Spell spell) {
        return spell.getWizardName();
    }

    protected Class<? extends Spell> getEntityType() {
        return Spell.class;
    }
}
