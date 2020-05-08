package fr.deltadesnoc.utils;

import fr.arinonia.arilibfx.AriLibFX;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FxAlert {
    private Alert alert;

    public FxAlert(Alert.AlertType type, String title, String header, String content, Exception exception, String lbl) {
        this.alert = new Alert(type);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        this.alert.setContentText(content);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();
        Label label = new Label(lbl);
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        DialogPane dialogPane = this.alert.getDialogPane();
        textArea.setMaxWidth(1.7976931348623157E308D);
        textArea.setMaxHeight(1.7976931348623157E308D);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        dialogPane.getStylesheets().add(AriLibFX.class.getResource("/fr/deltadesnoc/resources/css/dialogs.css").toExternalForm());
        dialogPane.getStyleClass().add("dialogs");
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(1.7976931348623157E308D);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        this.alert.getDialogPane().setExpandableContent(expContent);
    }

    public FxAlert(Alert.AlertType type, String title, String header, String content, Exception exception, String lbl, String cssPath, Class<?> cls) {
        this.alert = new Alert(type);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        this.alert.setContentText(content);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();
        Label label = new Label(lbl);
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        DialogPane dialogPane = this.alert.getDialogPane();
        textArea.setMaxWidth(1.7976931348623157E308D);
        textArea.setMaxHeight(1.7976931348623157E308D);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        dialogPane.getStylesheets().add(cls.getResource(cssPath).toExternalForm());
        dialogPane.getStyleClass().add("dialogs");
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(1.7976931348623157E308D);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        this.alert.getDialogPane().setExpandableContent(expContent);
    }

    public void setIcon(String image) {
        Stage stage = (Stage)this.alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(AriLibFX.loadImage(image));
    }

    public void setImage(String image, int x, int y) {
        ImageView imageView = new ImageView(AriLibFX.loadImage(image));
        imageView.setFitHeight((double)x);
        imageView.setFitWidth((double)y);
        this.alert.setGraphic(imageView);
    }

    public void show() {
        this.alert.showAndWait();
    }
}
