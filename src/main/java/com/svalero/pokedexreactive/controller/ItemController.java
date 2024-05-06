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
import com.svalero.pokedexreactive.model.Items.Item;
import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;
import com.svalero.pokedexreactive.task.ItemTask;

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

public class ItemController implements Initializable {
    
    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> attributeColumn;
    @FXML
    private TableColumn<Item, String> effectColumn;
    @FXML
    private TableColumn<Item, String> imageColumn;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label labelProgressStatus;
    @FXML
    private Button deleteItemButton;
    @FXML
    private Button downloadItemButton;

    private ObservableList<Item> items;
    private String requestedType;
    private ItemTask itemTask;

    public ItemController (String requestedType) {
        this.requestedType = requestedType;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("attributesString"));
        effectColumn.setCellValueFactory(new PropertyValueFactory<>("effectString"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("defaultSprite"));
        imageColumn.setCellFactory(col -> new SpriteCell<>());

        this.items = FXCollections.observableArrayList();
        System.out.println("Inicio");
        this.itemTableView.setItems(this.items);

        System.out.println("Busco " + requestedType);
        itemTask = new ItemTask(requestedType, this.items);
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(itemTask.progressProperty());
        itemTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
            labelProgressStatus.setText(newValue);
        });
        new Thread(itemTask).start();
    }
    
    @FXML
    private void deleteItem(ActionEvent event) {
        itemTableView.getItems().removeAll(itemTableView.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void downloadInfo(ActionEvent event) {
        ObservableList<Item> data = itemTableView.getItems();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String dateTimeString = now.format(formatter);
        File file = new File("item_" + requestedType + "_" + dateTimeString + "_data.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Nombre; Atributos; Efectos\n");    
            
            for (Item item : data) {
                String row = String.format("%s; %s; %s\n", item.getName(), item.getAttributesString(), item.getEffectString());
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
