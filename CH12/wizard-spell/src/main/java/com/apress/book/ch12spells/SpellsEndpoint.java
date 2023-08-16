package com.apress.book.ch12spells;

import java.util.Collection;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;
import javax.json.JsonObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/spell")
@ApplicationScoped
public class SpellsEndpoint {
    @Inject
    private SpellRepository spellRepository;

    @POST
    @Consumes(APPLICATION_JSON)
    public Spell createSpell(JsonObject spell) {
        Spell result = new Spell(spell.getString("wizardName"), spell.getString("spell"));
        return spellRepository.save(result);
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{wizardName}")
    public Spell findSpell(@PathParam("wizardName") String wizardName){
        return (spellRepository.get(wizardName));
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Collection<Spell> getSpells() {
        return spellRepository.getAll();
    }

    @DELETE
    @Path("{wizardName}")
    public Spell deleteSpell(@PathParam("wizardName") String wizardName) {
        return spellRepository.removeById(wizardName, true);
    }

    @PUT
    @Path("{wizardName}")
    @Consumes(APPLICATION_JSON)
    public Spell updateSpell(@PathParam("wizardName") String wizardName, Spell spell) {
        spellRepository.update(wizardName, Spell::setSpell, spell.getSpell());
        return findSpell(wizardName);
    }
}
