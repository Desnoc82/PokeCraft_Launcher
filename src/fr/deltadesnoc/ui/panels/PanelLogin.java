package fr.deltadesnoc.ui.panels;

import fr.deltadesnoc.Main;
import fr.deltadesnoc.auth.mineweb.AuthMineweb;
import fr.deltadesnoc.ui.PanelManager;
import fr.deltadesnoc.ui.panel.Panel;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PanelLogin extends Panel {

    private AuthMineweb authMineweb;

    private ImageView headSkin;

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        GridPane loginPanel = new GridPane();
        GridPane mainPane = new GridPane();
        GridPane bottomPane = new GridPane();

        loginPanel.setMaxWidth(400);
        loginPanel.setMinWidth(400);
        loginPanel.setMaxHeight(520);
        loginPanel.setMinHeight(520);

        GridPane.setHgrow(loginPanel, Priority.ALWAYS);
        GridPane.setVgrow(loginPanel, Priority.ALWAYS);
        GridPane.setHalignment(loginPanel, HPos.CENTER);
        GridPane.setValignment(loginPanel, VPos.CENTER);

        RowConstraints bottomContraints = new RowConstraints();
        bottomContraints.setValignment(VPos.BOTTOM);
        bottomContraints.setMaxHeight(55);
        loginPanel.getRowConstraints().addAll(new RowConstraints(), bottomContraints);
        loginPanel.add(mainPane, 0, 0);
        loginPanel.add(bottomPane, 0, 1);

        GridPane.setHgrow(mainPane, Priority.ALWAYS);
        GridPane.setVgrow(mainPane, Priority.ALWAYS);
        GridPane.setHgrow(bottomPane, Priority.ALWAYS);
        GridPane.setVgrow(bottomPane, Priority.ALWAYS);

        mainPane.setStyle("-fx-background-color: #181818");
        bottomPane.setStyle("-fx-background-color: #181818; -fx-opacity: 50%");
        Label noAccount = new Label("Vous n'avez pas encore de compte ?");
        Label registerHere = new Label("S'inscrire ici");

        GridPane.setHgrow(noAccount, Priority.ALWAYS);
        GridPane.setVgrow(noAccount, Priority.ALWAYS);
        GridPane.setHalignment(noAccount, HPos.CENTER);
        GridPane.setValignment(noAccount, VPos.TOP);
        noAccount.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 14px");

        GridPane.setHgrow(registerHere, Priority.ALWAYS);
        GridPane.setVgrow(registerHere, Priority.ALWAYS);
        GridPane.setHalignment(registerHere, HPos.CENTER);
        GridPane.setValignment(registerHere, VPos.BOTTOM);
        registerHere.setStyle("-fx-text-fill: #69a7ed; -fx-font-size: 14px");
        registerHere.setUnderline(true);
        registerHere.setTranslateY(-10);
        registerHere.setOnMouseEntered(e -> this.layout.setCursor(Cursor.HAND));
        registerHere.setOnMouseExited(e -> this.layout.setCursor(Cursor.DEFAULT));
        registerHere.setOnMouseClicked(e -> {
            openURL("https://pokecraft-fr.fr/");
        });

        bottomPane.getChildren().addAll(noAccount, registerHere);
        this.layout.getChildren().add(loginPanel);

        Label connectLabel = new Label("SE CONNECTER !");
        GridPane.setVgrow(connectLabel, Priority.ALWAYS);
        GridPane.setHgrow(connectLabel, Priority.ALWAYS);
        GridPane.setValignment(connectLabel, VPos.TOP);
        connectLabel.setTranslateY(27);
        connectLabel.setTranslateX(37.5);
        connectLabel.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 16px;");

        Separator connectSeparator = new Separator();
        GridPane.setVgrow(connectSeparator, Priority.ALWAYS);
        GridPane.setHgrow(connectSeparator, Priority.ALWAYS);
        GridPane.setValignment(connectSeparator, VPos.TOP);
        GridPane.setHalignment(connectSeparator, HPos.CENTER);
        connectSeparator.setTranslateY(60);
        connectSeparator.setMinWidth(325);
        connectSeparator.setMaxWidth(325);
        connectSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 50%;");

        Label usernameLabel = new Label("Nom d'utilisateur");
        GridPane.setVgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setHgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setValignment(usernameLabel, VPos.TOP);
        GridPane.setHalignment(usernameLabel, HPos.LEFT);
        usernameLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        usernameLabel.setTranslateX(37.5);
        usernameLabel.setTranslateY(110);

        TextField usernameField = new TextField();
        GridPane.setVgrow(usernameField, Priority.ALWAYS);
        GridPane.setHgrow(usernameField, Priority.ALWAYS);
        GridPane.setHalignment(usernameField, HPos.LEFT);
        GridPane.setValignment(usernameField, VPos.TOP);
        usernameField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5;");
        usernameField.setMaxWidth(325);
        usernameField.setMaxHeight(40);
        usernameField.setTranslateX(37.5);
        usernameField.setTranslateY(140);

        Separator usernameSeparator = new Separator();
        GridPane.setVgrow(usernameSeparator, Priority.ALWAYS);
        GridPane.setHgrow(usernameSeparator, Priority.ALWAYS);
        GridPane.setHalignment(usernameSeparator, HPos.CENTER);
        GridPane.setValignment(usernameSeparator, VPos.TOP);
        usernameSeparator.setTranslateY(181);
        usernameSeparator.setMaxWidth(325);
        usernameSeparator.setMinWidth(325);
        usernameSeparator.setMaxHeight(1);
        usernameSeparator.setMinHeight(1);
        usernameSeparator.setStyle("-fx-opacity: 40%");

        Label passwordLabel = new Label("Mot de passe");
        GridPane.setVgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setHgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setHalignment(passwordLabel, HPos.LEFT);
        GridPane.setValignment(passwordLabel, VPos.TOP);
        passwordLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        passwordLabel.setTranslateX(37.5);
        passwordLabel.setTranslateY(200);

        PasswordField passwordField = new PasswordField();
        GridPane.setVgrow(passwordField, Priority.ALWAYS);
        GridPane.setHgrow(passwordField, Priority.ALWAYS);
        GridPane.setHalignment(passwordField, HPos.LEFT);
        GridPane.setValignment(passwordField, VPos.TOP);
        passwordField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5;");
        passwordField.setMaxWidth(325);
        passwordField.setMaxHeight(40);
        passwordField.setTranslateX(37.5);
        passwordField.setTranslateY(230);

        Separator passwordSeparator = new Separator();
        GridPane.setVgrow(passwordSeparator, Priority.ALWAYS);
        GridPane.setHgrow(passwordSeparator, Priority.ALWAYS);
        GridPane.setHalignment(passwordSeparator, HPos.CENTER);
        GridPane.setValignment(passwordSeparator, VPos.TOP);
        passwordSeparator.setTranslateY(271);
        passwordSeparator.setMaxWidth(325);
        passwordSeparator.setMinWidth(325);
        passwordSeparator.setMaxHeight(1);
        passwordSeparator.setMinHeight(1);
        passwordSeparator.setStyle("-fx-opacity: 40%");

        Label forgotPasswordLabel = new Label("Mot de passe oublié ?");
        GridPane.setVgrow(forgotPasswordLabel, Priority.ALWAYS);
        GridPane.setHgrow(forgotPasswordLabel, Priority.ALWAYS);
        GridPane.setHalignment(forgotPasswordLabel, HPos.LEFT);
        GridPane.setValignment(forgotPasswordLabel, VPos.CENTER);
        forgotPasswordLabel.setStyle("-fx-text-fill: #69a7ed; -fx-font-size: 12px");
        forgotPasswordLabel.setUnderline(true);
        forgotPasswordLabel.setTranslateX(37.5);
        forgotPasswordLabel.setTranslateY(60);

        forgotPasswordLabel.setOnMouseEntered(e -> {
            this.layout.setCursor(Cursor.HAND);
        });
        forgotPasswordLabel.setOnMouseExited(e -> {
            this.layout.setCursor(Cursor.DEFAULT);
        });
        forgotPasswordLabel.setOnMouseClicked(e -> {
            openURL("https://pokecraft-fr.fr/");
        });

        Button connectButton = new Button("Se connecter");
        GridPane.setVgrow(connectButton, Priority.ALWAYS);
        GridPane.setHgrow(connectButton, Priority.ALWAYS);
        GridPane.setHalignment(connectButton, HPos.LEFT);
        GridPane.setValignment(connectButton, VPos.CENTER);
        connectButton.setTranslateX(37.5);
        connectButton.setTranslateY(110);
        connectButton.setMinWidth(325);
        connectButton.setMinHeight(50);
        connectButton.setStyle("-fx-background-color: #34aa2f; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: white; ");
        connectButton.setOnMouseEntered(e -> {
            this.layout.setCursor(Cursor.HAND);
            connectButton.setStyle("-fx-background-color: rgb(0,190,10); -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: white; ");
        });
        connectButton.setOnMouseExited(e -> {
            this.layout.setCursor(Cursor.DEFAULT);
            connectButton.setStyle("-fx-background-color: #34aa2f; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: white; ");
        });
       connectButton.setOnMouseClicked(e-> {
           this.authMineweb = new AuthMineweb(usernameField.getText(), passwordField.getText());
           if (authMineweb.isConnected()) {
               Main.logger.log("Vous êtes connecté en tant que " + authMineweb.getInfo("pseudo") + "\n rank : " +
                       authMineweb.getInfo("rank") + ", money : " + authMineweb.getInfo("money") + " points");
               this.setHeadSkin(new ImageView("https://minotar.net/avatar/" + this.getAuthMineweb().getInfo("pseudo") + "/100.png"));
               this.panelManager.showPanel(new PanelHome(this));
           }else{
               Main.logger.warn("Erreur");
           }
        });
       loginPanel.setOnKeyPressed(event -> {
           if(event.getCode().equals(KeyCode.ENTER)){
               this.authMineweb = new AuthMineweb(usernameField.getText(), passwordField.getText());
               if (authMineweb.isConnected()) {
                   Main.logger.log("Vous êtes connecté en tant que " + authMineweb.getInfo("pseudo") + "\n rank : " +
                           authMineweb.getInfo("rank") + ", money : " + authMineweb.getInfo("money") + " points");
                   this.setHeadSkin(new ImageView("https://minotar.net/avatar/" + this.getAuthMineweb().getInfo("pseudo") + "/100.png"));
                   this.panelManager.showPanel(new PanelHome(this));
               }else{
                   Main.logger.warn("Erreur");
               }
           }
       });

        mainPane.getChildren().addAll(connectLabel, connectSeparator, usernameLabel, usernameField,usernameSeparator,passwordLabel,passwordField,
                passwordSeparator,forgotPasswordLabel,connectButton);
    }

    private void openURL(String url){
        try {
            Desktop.getDesktop().browse(new URI(url));
        }catch (URISyntaxException | IOException e){
            Main.logger.warn(e.getMessage());
        }
    }

    public AuthMineweb getAuthMineweb() {
        return authMineweb;
    }

    public ImageView getHeadSkin() {
        return headSkin;
    }

    public void setHeadSkin(ImageView headSkin) {
        this.headSkin = headSkin;
    }
}
