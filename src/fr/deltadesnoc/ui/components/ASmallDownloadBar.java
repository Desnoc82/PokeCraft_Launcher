package fr.deltadesnoc.ui.components;

import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ASmallDownloadBar extends GridPane {

    private Rectangle backgroundRect = new Rectangle();
    private Rectangle foregroundRect = new Rectangle();


    public ASmallDownloadBar() {
        GridPane.setVgrow(backgroundRect, Priority.ALWAYS);
        GridPane.setHgrow(backgroundRect, Priority.ALWAYS);
        GridPane.setValignment(backgroundRect, VPos.BOTTOM);
        GridPane.setVgrow(foregroundRect, Priority.ALWAYS);
        GridPane.setHgrow(foregroundRect, Priority.ALWAYS);
        GridPane.setValignment(foregroundRect, VPos.BOTTOM);
        backgroundRect.setFill(Color.rgb(222,222,222,0.3));
        backgroundRect.setWidth(170);
        backgroundRect.setHeight(3);
        foregroundRect.setFill(Color.rgb(255,255,255));
        foregroundRect.setHeight(3);
        this.getChildren().addAll(backgroundRect, foregroundRect);


    }

    public float percentage(float val, float max){
        return val * 170 / max;
    }

    public void setProgress(float val, float max){
        foregroundRect.setWidth(percentage(val,max));
    }

}