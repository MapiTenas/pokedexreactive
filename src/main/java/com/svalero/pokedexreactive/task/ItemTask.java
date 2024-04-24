package com.svalero.pokedexreactive.task;

import java.util.concurrent.atomic.AtomicInteger;

import com.svalero.pokedexreactive.model.Items.Item;
import com.svalero.pokedexreactive.service.PokemonService;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class ItemTask extends Task<Integer> {

    private String requestedItem;
    private ObservableList<Item> items;

    public ItemTask (String requestedItem, ObservableList<Item> items) {
        this.requestedItem = requestedItem;
        this.items = items;   
    }

    @Override 
    protected Integer call() throws Exception {
        PokemonService pokemonService = new PokemonService();
        AtomicInteger totalItemCount = new AtomicInteger();

        Observable<Integer> totalItemCountObservable = pokemonService.getTotalItemCount(requestedItem);
        totalItemCountObservable.subscribe(count -> {totalItemCount.set(count);});

        Consumer<Item> user = (itemInfo) -> {
            itemInfo.setDataForTable();
            Thread.sleep(250);

            Platform.runLater(() -> {
                items.add(itemInfo);
                
                updateProgress(items.size(), totalItemCount.get());
                float totalProcessed = items.size() / (float) totalItemCount.get();
                String totalProcessedFormatted = String.format("%.0f", 100 * totalProcessed);
                updateMessage(totalProcessedFormatted + " %");
            });
        };

        pokemonService.getItems(requestedItem).subscribe(user);
        return null;

    }
}
