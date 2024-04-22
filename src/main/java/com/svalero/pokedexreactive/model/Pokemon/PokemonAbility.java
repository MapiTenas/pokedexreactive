package com.svalero.pokedexreactive.model.Pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonAbility {
    Ability ability;
    Integer slot;
}
