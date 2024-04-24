package com.svalero.pokedexreactive.task;

import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;
import com.svalero.pokedexreactive.service.PokemonService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import java.util.concurrent.atomic.AtomicInteger;
import io.reactivex.Observable;

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
        AtomicInteger totalPokemonCount = new AtomicInteger();

        Observable<Integer> totalPokemonCountObservable = pokemonService.getTotalPokemonCount(requestedPokemon);
        totalPokemonCountObservable.subscribe(count -> {totalPokemonCount.set(count);});

        Consumer<PokemonInfo> user = (pokemonInfo) -> {
            pokemonInfo.setDataForTable();
            Thread.sleep(250);

            Platform.runLater(() -> {
                pokemons.add(pokemonInfo);
                
                updateProgress(pokemons.size(), totalPokemonCount.get());
                float totalProcessed = pokemons.size() / (float) totalPokemonCount.get();
                String totalProcessedFormatted = String.format("%.0f", 100 * totalProcessed);
                updateMessage(totalProcessedFormatted + " %");
            });
        };

        pokemonService.getPokemons(requestedPokemon).subscribe(user);
        return null;

    }

}
