package fr.deltadesnoc.ui.components;

import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class ABigDownloadBar extends GridPane {

    private Rectangle backgroundRect = new Rectangle();
    private Rectangle foregroundRect = new Rectangle();
    private Rectangle backgroundBorderRect = new Rectangle();
    private Label infoLabel = new Label();
    private int width = 400;
    public ABigDownloadBar() {
        GridPane.setVgrow(backgroundRect, Priority.ALWAYS);
        GridPane.setHgrow(backgroundRect, Priority.ALWAYS);
        GridPane.setValignment(backgroundRect, VPos.BOTTOM);
        GridPane.setVgrow(foregroundRect, Priority.ALWAYS);
        GridPane.setHgrow(foregroundRect, Priority.ALWAYS);
        GridPane.setValignment(foregroundRect, VPos.BOTTOM);
        GridPane.setVgrow(backgroundBorderRect, Priority.ALWAYS);
        GridPane.setHgrow(backgroundBorderRect, Priority.ALWAYS);
        GridPane.setValignment(backgroundBorderRect, VPos.BOTTOM);
        GridPane.setVgrow(infoLabel, Priority.ALWAYS);
        GridPane.setHgrow(infoLabel, Priority.ALWAYS);
        GridPane.setValignment(infoLabel, VPos.BOTTOM);
        backgroundBorderRect.setFill(Color.rgb(41,76,114));
        backgroundBorderRect.setWidth(width);
        backgroundBorderRect.setHeight(20);
        backgroundRect.setFill(Color.rgb(3,48,90));
        backgroundRect.setWidth(width-2);
        backgroundRect.setHeight(18);
        backgroundRect.setTranslateY(-1);
        backgroundRect.setTranslateX(1);
        Stop[] stops = new Stop[] { new Stop(0, Color.rgb(7,85,136)), new Stop(1, Color.rgb(3,163,219))};
        LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        foregroundRect.setFill(lg);
        foregroundRect.setHeight(18);
        foregroundRect.setTranslateY(-1);
        foregroundRect.setTranslateX(1);
        infoLabel.setTranslateY(-2);
        infoLabel.setTranslateX(20);
        infoLabel.setStyle("-fx-text-fill: #fff");
        this.setMaxSize(width,18);
        GridPane.setValignment(this, VPos.BOTTOM);
        this.getChildren().addAll(backgroundBorderRect,backgroundRect, foregroundRect,infoLabel);
    }

    private float percentage(float val, float max){
        return val * (width-2) / max;
    }

    public void setProgress(float val, float max){
        foregroundRect.setWidth(percentage(val,max));
    }

    public void setInfoLabel(String s) {
        infoLabel.setText(s);
    }
}