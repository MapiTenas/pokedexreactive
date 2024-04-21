package com.svalero.pokedexreactive.controller;

import java.util.ResourceBundle;

import com.svalero.pokedexreactive.Utils.Constants;
import com.svalero.pokedexreactive.task.PokemonTask;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class AppController implements Initializable {
    // Match of the components of the user interface with the controller.
    private ObservableList<String> pokeNames;
    @FXML
    private ComboBox<String> pokeTypeComboBox;
    @FXML
    private Button buttonSearch;
    @FXML
    private ListView<String> pokeListView;
    /*@FXML
    private TabPane tabPaneResults;*/
    private PokemonTask pokemonTask;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        //I load the list of constants into the combobox
        ObservableList<String> options = FXCollections.observableArrayList(Constants.pokeTypes);
        pokeTypeComboBox.setItems(options);
        //tabPaneResults.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }

    @FXML
    public void searchPokemon(ActionEvent event){
        this.pokeNames = FXCollections.observableArrayList();
        System.out.println("Inicio");
        String requestedType = pokeTypeComboBox.getValue().toString();
        this.pokeListView.setItems(this.pokeNames); //Binds the list observable to the listview
        
        System.out.println("Busco " + requestedType);
        pokemonTask = new PokemonTask(requestedType, this.pokeNames);
        new Thread(pokemonTask).start();
    }

}
