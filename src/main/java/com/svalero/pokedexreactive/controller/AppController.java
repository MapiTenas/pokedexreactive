package com.svalero.pokedexreactive.controller;

import java.util.ResourceBundle;

import com.svalero.pokedexreactive.Utils.Constants;
import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class AppController implements Initializable {
    // Match of the components of the user interface with the controller.
    @FXML
    private ComboBox<String> pokeTypeComboBox;
    @FXML
    private Button buttonSearch;
    @FXML
    private TabPane tabPaneResults;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        //I load the list of constants into the combobox
        ObservableList<String> options = FXCollections.observableArrayList(Constants.pokeTypes);
        pokeTypeComboBox.setItems(options);
        tabPaneResults.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }

    /*@FXML
    public void searchPokemon(ActionEvent event){
        this.pokemons = FXCollections.observableArrayList();
        System.out.println("Inicio");
        String requestedType = pokeTypeComboBox.getValue().toString();
        this.pokeTableView.setItems(this.pokemons); //Binds the list observable to the listview
        
        System.out.println("Busco " + requestedType);
        pokemonTask = new PokemonTask(requestedType, this.pokemons);
        new Thread(pokemonTask).start();
    }*/

    @FXML 
    public void searchPokemon(ActionEvent event){
        
        String requestedType = pokeTypeComboBox.getValue().toString();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pokeWindow.fxml"));
        PokemonController pokemonController = new PokemonController(requestedType);
        loader.setController(pokemonController);
        try {
            AnchorPane pokePane = loader.load();
            tabPaneResults.getTabs().add(new Tab(requestedType, pokePane));
        } catch (IOException e) {
            e.printStackTrace();
        }

    } 

}
