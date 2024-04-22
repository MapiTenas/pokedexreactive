package com.svalero.pokedexreactive.task;

import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;
import com.svalero.pokedexreactive.service.PokemonService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class PokemonTask extends Task<Integer> {

    private String requestedPokemon;
    private ObservableList<String> pokeNames;

    public PokemonTask (String requestedPokemon, ObservableList<String> pokeNames) {
        this.requestedPokemon = requestedPokemon;
        this.pokeNames = pokeNames;   
    }


    @Override 
    protected Integer call() throws Exception {
        PokemonService pokemonService = new PokemonService();

        Consumer<PokemonInfo> user = (pokemonInfo) -> {
            Thread.sleep(250);
            Platform.runLater(()-> this.pokeNames.add(pokemonInfo.getId() + " - " + pokemonInfo.getName() + " - " + pokemonInfo.showAllAbilities()));
        };

        pokemonService.getPokemons(requestedPokemon).subscribe(user);
        return null;

    }

}
