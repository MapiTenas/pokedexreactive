package com.svalero.pokedexreactive.task;

import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;
import com.svalero.pokedexreactive.service.PokemonService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class PokemonTask extends Task<Integer> {

    private String requestedPokemon;
    private ObservableList<PokemonInfo> pokemons;

    public PokemonTask (String requestedPokemon, ObservableList<PokemonInfo> pokemons) {
        this.requestedPokemon = requestedPokemon;
        this.pokemons = pokemons;   
    }


    @Override 
    protected Integer call() throws Exception {
        PokemonService pokemonService = new PokemonService();

        Consumer<PokemonInfo> user = (pokemonInfo) -> {
            pokemonInfo.setDataForTable();
            Thread.sleep(250);
            Platform.runLater(()-> this.pokemons.add(pokemonInfo));
        };

        pokemonService.getPokemons(requestedPokemon).subscribe(user);
        return null;

    }

}
