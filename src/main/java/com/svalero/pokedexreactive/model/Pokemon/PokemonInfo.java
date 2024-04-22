package com.svalero.pokedexreactive.model.Pokemon;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PokemonInfo {
    String id;
    String name;
    Sprites sprites;
    ArrayList<PokemonAbility> abilities;

    public String showAllAbilities() {
        ArrayList<String> abilityNames = new ArrayList<String>();
        for(PokemonAbility slot : this.getAbilities()){
            abilityNames.add(slot.getAbility().getName());
        }
        return abilityNames.toString();
    }

}