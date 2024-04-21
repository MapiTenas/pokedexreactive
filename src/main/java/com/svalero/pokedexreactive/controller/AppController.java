package com.svalero.pokedexreactive.controller;

import java.util.ResourceBundle;

import com.svalero.pokedexreactive.task.PokemonTask;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class AppController {
    // Match of the components of the user interface with the controller.
    private ObservableList<String> pokeNames;
    @FXML
    private TextField searchInput;
    @FXML
    private Button buttonSearch;
    @FXML
    private ListView<String> pokeListView;
    /*@FXML
    private TabPane tabPaneResults;*/
    private PokemonTask pokemonTask;

    //public AppControler() { (Da error, ¿por qué? Él no la tiene en la suya, pero si que sale en la de los filtros.)

    //}

    /*@Override
    public void initialize (URL location, ResourceBundle resources) {
        tabPaneResults.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }*/
    @FXML
    public void searchPokemon(ActionEvent event){
        this.pokeNames = FXCollections.observableArrayList();
        System.out.println("Inicio");
        String requestedType = searchInput.getText();
        searchInput.clear();
        searchInput.requestFocus();
        this.pokeListView.setItems(this.pokeNames); //Enlaza el observable list con el listview
        
        System.out.println("Busco " + requestedType);
        pokemonTask = new PokemonTask(requestedType, this.pokeNames);
        new Thread(pokemonTask).start();
    }



}
