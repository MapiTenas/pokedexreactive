package com.svalero.pokedexreactive.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.svalero.pokedexreactive.Utils.SpriteCell;
import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;
import com.svalero.pokedexreactive.task.PokemonTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PokemonController implements Initializable {

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
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label labelProgressStatus;
    @FXML
    private Button deletePokeButton;
    
    private ObservableList<PokemonInfo> pokemons;
    private String requestedType;
    private PokemonTask pokemonTask;


    public PokemonController (String requestedType) {
        this.requestedType = requestedType;
    }

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        //I give value to the columns of the tableview
        frontColumn.setCellValueFactory(new PropertyValueFactory<>("frontSprite"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        abilitiesColumn.setCellValueFactory(new PropertyValueFactory<>("abilitiesString"));
        backColumn.setCellValueFactory(new PropertyValueFactory<>("backSprite"));
        frontColumn.setCellFactory(col -> new SpriteCell<>());
        backColumn.setCellFactory(col -> new SpriteCell<>());
        
        this.pokemons = FXCollections.observableArrayList();
        System.out.println("Inicio");
        this.pokeTableView.setItems(this.pokemons); //Binds the list observable to the listview
        
        System.out.println("Busco " + requestedType);
        pokemonTask = new PokemonTask(requestedType, this.pokemons);
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(pokemonTask.progressProperty());
        pokemonTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
            labelProgressStatus.setText(newValue);
        });
        new Thread(pokemonTask).start();

    }

    @FXML
    private void deletePokemon(ActionEvent event) {
        pokeTableView.getItems().removeAll(pokeTableView.getSelectionModel().getSelectedItems());
    }

}
