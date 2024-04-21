package com.svalero.pokedexreactive.model.Pokemon;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PokemonInfo {
    String id;
    String name;
    List<Sprites> sprites;
    List<Abilities> abilities; 
}
