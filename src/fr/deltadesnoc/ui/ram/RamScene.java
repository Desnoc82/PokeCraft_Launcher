package fr.deltadesnoc.ui.ram;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

import fr.deltadesnoc.ui.panels.PanelHome;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class RamScene extends Parent{

    private File ramfile = new File(PanelHome.DIR, "ram.properties");

    public RamScene() {

        if(!ramfile.exists()){
            try {
                ramfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Rectangle rectangle = new Rectangle();
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setWidth(500);
        rectangle.setHeight(400);
        rectangle.setFill(Color.rgb(59, 59, 59));
        rectangle.setArcHeight(105);
        rectangle.setArcWidth(50);

        Rectangle bar = new Rectangle();
        bar.setX(4);
        bar.setY(-1);
        bar.setWidth(492);
        bar.setHeight(40);
        bar.setFill(Color.rgb(30, 30, 30));
        bar.setArcHeight(50);
        bar.setArcWidth(50);

        Label label = new Label();
        label.setText(ramfile.exists() ? RamManager.getRam().toString() + "Go" : "1Go");
        label.setLayoutX(198);
        label.setLayoutY(80);
        label.setStyle("-fx-text-fill: white; -fx-font-size: 64px;");
        Slider slider = new Slider();
        slider.setPrefWidth(400.0d);
        slider.setPrefHeight(20.0d);
        slider.setLayoutX(50);
        slider.setLayoutY(250);
        slider.setMin(1.0d);
        slider.setMax(getPhysicalMemoryBytes());
        slider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
                label.setText(newValue.intValue() + "Go");
                RamManager.setRam(newValue.intValue() + "Go");

            }
        });
        slider.setValue(ramfile.exists() ? Integer.parseInt(RamManager.getRam()) : 1);
        slider.setStyle("-fx-control-inner-background: rgb(3,163,219);");

        Line line = new Line();
        line.setStartX(400.0f);
        line.setStartY(200.0f);
        line.setEndX(487.0f);
        line.setEndY(34.0f);
        line.setStrokeWidth(2);
        line.setStroke(Color.rgb(30, 30, 30));

        Line line2 = new Line();
        line2.setStartX(14.0f);
        line2.setStartY(34.0f);
        line2.setEndX(87.0f);
        line2.setEndY(200);
        line2.setStrokeWidth(2);
        line2.setStroke(Color.rgb(30, 30, 30));

        Line line3 = new Line(400,200,86,200);
        line2.setStrokeWidth(2);
        line2.setStroke(Color.rgb(30, 30, 30));

        Button button = new Button("Valider");
        button.setPrefSize(120, 40);
        button.setStyle("-fx-background-color: rgb(3,163,219);");
        button.setLayoutX(198);
        button.setLayoutY(300);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                button.setStyle("-fx-background-color: rgb(42, 42, 42); -fx-text-fill: rgb(3,163,219);");
            }

        });
        button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                button.setStyle("-fx-background-color: rgb(3,163,219); -fx-text-fill: white;");
            }

        });
        button.setOnAction((e)->{
            RamPanel.getStage().close();
        });
        getChildren().add(rectangle);
        getChildren().add(slider);
        getChildren().add(bar);
        getChildren().add(line);
        getChildren().add(line2);
        getChildren().add(line3);
        getChildren().add(label);
        getChildren().add(button);

    }


    public static final long getPhysicalMemoryBytes() {
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize()  / (1024 * 1024 * 1024);
    }

}
