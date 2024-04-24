package com.svalero.pokedexreactive.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.svalero.pokedexreactive.Utils.SpriteCell;
import com.svalero.pokedexreactive.model.Items.Item;
import com.svalero.pokedexreactive.task.ItemTask;

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


    
}
