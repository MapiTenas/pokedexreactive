package com.svalero.pokedexreactive.Utils;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteCell <S, T> extends TableCell<S, T> {

    private final ImageView imageView;

    public SpriteCell() {
        this.imageView = new ImageView();
        this.imageView.setFitHeight(50);
        this.imageView.setFitWidth(50);
        setGraphic(imageView);
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            imageView.setImage(null);
            setText(null);
            setGraphic(null);
        } else {
            Image image = new Image(item.toString());
            imageView.setImage(image);
            setGraphic(imageView);
        }
    }
}
