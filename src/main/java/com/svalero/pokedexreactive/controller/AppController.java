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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AppController implements Initializable {
    // Match of the components of the user interface with the controller.
    @FXML
    private ComboBox<String> pokeTypeComboBox;
    @FXML
    private Button buttonSearch;
    @FXML
    private TabPane tabPaneResults;
    @FXML
    private ComboBox<String> itemCategoryComboBox;
    @FXML
    private Button buttonSearchItems;
    @FXML
    private ImageView pokedexLogo;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        //I load the list of constants into the combobox
        ObservableList<String> pokeOptions = FXCollections.observableArrayList(Constants.pokeTypes);
        pokeTypeComboBox.setItems(pokeOptions);
        ObservableList<String> itemOptions = FXCollections.observableArrayList(Constants.itemTypes);
        itemCategoryComboBox.setItems(itemOptions);
        tabPaneResults.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        pokedexLogo.setImage(new Image(getClass().getResourceAsStream("/Pokedexlogo.png")));    
    }

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


    @FXML
    public void searchItems(ActionEvent event){
        String requestedCategory = itemCategoryComboBox.getValue().toString();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemWindow.fxml"));
        ItemController itemController = new ItemController(requestedCategory);
        loader.setController(itemController);
        try {
            AnchorPane itemPane = loader.load();
            tabPaneResults.getTabs().add(new Tab(requestedCategory, itemPane));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    

    

}
