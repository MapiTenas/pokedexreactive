package com.svalero.pokedexreactive.controller;

import java.util.ResourceBundle;

import com.svalero.pokedexreactive.Utils.Constants;
import com.svalero.pokedexreactive.Utils.SpriteCell;
import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppController implements Initializable {
    // Match of the components of the user interface with the controller.
    private ObservableList<PokemonInfo> pokemons;
    @FXML
    private ComboBox<String> pokeTypeComboBox;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<PokemonInfo> pokeTableView;
    @FXML
    private TableColumn<PokemonInfo, String> frontColumn;
    @FXML
    private TableColumn<PokemonInfo, String> nameColumn;
    @FXML
    private TableColumn<PokemonInfo, String> numberColumn;
    @FXML
    private TableColumn<PokemonInfo, String> abilitiesColumn;
    @FXML
    private TableColumn<PokemonInfo, String> backColumn;

    /*@FXML
    private TabPane tabPaneResults;*/
    private PokemonTask pokemonTask;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        //I load the list of constants into the combobox
        ObservableList<String> options = FXCollections.observableArrayList(Constants.pokeTypes);
        pokeTypeComboBox.setItems(options);
        //
        frontColumn.setCellValueFactory(new PropertyValueFactory<>("frontSprite"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        abilitiesColumn.setCellValueFactory(new PropertyValueFactory<>("abilitiesString"));
        backColumn.setCellValueFactory(new PropertyValueFactory<>("backSprite"));
        frontColumn.setCellFactory(col -> new SpriteCell<>());
        backColumn.setCellFactory(col -> new SpriteCell<>());
        //tabPaneResults.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }

    @FXML
    public void searchPokemon(ActionEvent event){
        this.pokemons = FXCollections.observableArrayList();
        System.out.println("Inicio");
        String requestedType = pokeTypeComboBox.getValue().toString();
        this.pokeTableView.setItems(this.pokemons); //Binds the list observable to the listview
        
        System.out.println("Busco " + requestedType);
        pokemonTask = new PokemonTask(requestedType, this.pokemons);
        new Thread(pokemonTask).start();
    }

}
