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
    String abilitiesString;
    String frontSprite;
    String backSprite;

    public void setDataForTable() {
        ArrayList<String> abilityNames = new ArrayList<String>();
        for(PokemonAbility slot : this.getAbilities()){
            abilityNames.add(slot.getAbility().getName());
        }
        this.abilitiesString = abilityNames.toString().replaceAll("\\[|\\]", "");
        this.frontSprite = this.sprites.getFront_default();
        this.backSprite = this.sprites.getBack_default();
    }

}