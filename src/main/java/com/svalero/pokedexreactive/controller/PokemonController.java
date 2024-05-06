package com.svalero.pokedexreactive.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.svalero.pokedexreactive.Utils.SpriteCell;
import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;
import com.svalero.pokedexreactive.task.PokemonTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    @FXML
    private Button downloadPokeButton;

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
        this.pokeTableView.setItems(this.pokemons); //Binds the list observable to the tableview
        
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

    @FXML
    private void downloadInfo(ActionEvent event) {
        ObservableList<PokemonInfo> data = pokeTableView.getItems();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String dateTimeString = now.format(formatter);
        File file = new File("pokemon_" + requestedType + "_" + dateTimeString + "_data.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Nombre; Numero Pokedex; Habilidades\n");    
            
            for (PokemonInfo pokemon : data) {
                String row = String.format("%s; %s; %s\n", pokemon.getName(), pokemon.getId(), pokemon.getAbilitiesString());
                writer.write(row);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The information has been saved.");
                alert.show();
        
        } catch (IOException e) {
            // TODO: handle exception
        }

    }

}
