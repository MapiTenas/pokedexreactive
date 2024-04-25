package com.svalero.pokedexreactive;

import java.io.IOException;

import com.svalero.pokedexreactive.controller.AppController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class App extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("Initializing the application.");
        System.out.println(Thread.currentThread().getName());
        super.init();
    }
    
    @Override
    public void start (Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainWindowP.fxml"));
        loader.setController(new AppController());
        AnchorPane anchorPane = loader.load();
        Image appIcon = new Image("/appicon.png");
        

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Reactive Pokedex");
        primaryStage.getIcons().add(appIcon);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception{
        System.out.println("Exiting the application.");
        System.out.println(Thread.currentThread().getName());
        super.stop();
    }
    
    public static void main(String[] args) {
        launch();
    }
}