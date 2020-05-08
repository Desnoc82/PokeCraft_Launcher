package fr.deltadesnoc.ui.panels;

import fr.deltadesnoc.ui.PanelManager;
import fr.deltadesnoc.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PanelProfil extends Panel {

    private PanelLogin panelLogin;

    public PanelProfil(PanelLogin panelLogin) { this.panelLogin = panelLogin; }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        showTopBar();

        GridPane profilPanel = new GridPane();

        profilPanel.setMaxWidth(850);
        profilPanel.setMinWidth(850);
        profilPanel.setMaxHeight(550);
        profilPanel.setMinHeight(550);

        GridPane.setVgrow(profilPanel, Priority.ALWAYS);
        GridPane.setHgrow(profilPanel, Priority.ALWAYS);
        GridPane.setValignment(profilPanel, VPos.CENTER);
        GridPane.setHalignment(profilPanel, HPos.CENTER);

        profilPanel.setStyle("-fx-background-color: #181818; -fx-opacity: 80%;");

        ImageView skin = panelLogin.getHeadSkin();
        skin.setFitHeight(160);
        skin.setFitWidth(160);
        skin.setTranslateX(50);
        skin.setTranslateY(50);

        Label pseudoT = new Label("Pseudo :");
        Rectangle pseudo = new Rectangle();
        Label pseudoL = new Label(panelLogin.getAuthMineweb().getInfo("pseudo"));
        pseudoT.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 17px;");
        pseudoT.setTranslateX(50);
        pseudoT.setTranslateY(skin.getFitHeight() + 10);
        pseudo.setWidth(220);
        pseudo.setHeight(45);
        pseudo.setFill(Color.rgb(37, 41, 45));
        pseudo.setTranslateX(50);
        pseudo.setTranslateY(skin.getFitHeight() + skin.getTranslateY() + 10);
        pseudoL.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        pseudoL.setTranslateX(55);
        pseudoL.setTranslateY(skin.getFitHeight() + skin.getTranslateY() + 11);

        Label emailT = new Label("Email :");
        Rectangle email = new Rectangle();
        Label emailL = new Label(panelLogin.getAuthMineweb().getInfo("email"));
        emailT.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 17px;");
        emailT.setTranslateX(50);
        emailT.setTranslateY(skin.getFitHeight() + 110);
        email.setWidth(220);
        email.setHeight(45);
        email.setFill(Color.rgb(37, 41, 45));
        email.setTranslateX(50);
        email.setTranslateY(skin.getFitHeight() + skin.getTranslateY() + 115);
        emailL.setStyle("-fx-text-fill: white; -fx-font-size: 15px;"); //Couleur de base : #8E8787
        emailL.setTranslateX(55);
        emailL.setTranslateY(skin.getFitHeight() + skin.getTranslateY() + 116);

        Label rankT = new Label("Grade :");
        Rectangle rank = new Rectangle();
        Label rankL = new Label(panelLogin.getAuthMineweb().getInfo("rank"));

        rankT.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 17px;");
        rankT.setTranslateX(555);
        rankT.setTranslateY(skin.getFitHeight() + 10);
        rank.setWidth(220);
        rank.setHeight(45);
        rank.setFill(Color.rgb(37, 41, 45));
        rank.setTranslateX(555);
        rank.setTranslateY(skin.getFitHeight() + skin.getTranslateY() + 10);
        rankL.setTranslateX(560);
        rankL.setTranslateY(skin.getFitHeight() + skin.getTranslateY() + 11);

        Label moneyT = new Label("PokeCoins :");
        Rectangle money = new Rectangle();
        Label moneyL = new Label(panelLogin.getAuthMineweb().getInfo("money"));
        moneyT.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 17px;");
        moneyT.setTranslateX(555);
        moneyT.setTranslateY(skin.getFitHeight() + 110);
        money.setWidth(220);
        money.setHeight(45);
        money.setFill(Color.rgb(37, 41, 45));
        money.setTranslateX(555);
        money.setTranslateY(skin.getFitHeight() + skin.getTranslateY() + 115);
        moneyL.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        moneyL.setTranslateX(560);
        moneyL.setTranslateY(skin.getFitHeight() + skin.getTranslateY() + 116);
        switch (panelLogin.getAuthMineweb().getInfo("rank")){
            case "Administrateur":
                rankL.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 20px;");
                break;
            case "Modérateur" :
                rankL.setStyle("-fx-text-fill: #e67e22; -fx-font-size: 20px;");
                break;
            case "Architecte":
                rankL.setStyle("-fx-text-fill: #2fab00; -fx-font-size: 20px;");
                break;
            case "Modérateur-Joueur":
                rankL.setStyle("-fx-text-fill: #faff00; -fx-font-size: 20px;");
                break;
            case "Guide":
                rankL.setStyle("-fx-text-fill: #ff00ff; -fx-font-size: 20px;");
                break;
            case "PokéMaster":
                rankL.setStyle("-fx-text-fill: #002ead; -fx-font-size: 20px;");
                break;
            case "PokéEpic":
                rankL.setStyle("-fx-text-fill: #215cff; -fx-font-size: 20px;");
                break;
            case "PokéElite":
                rankL.setStyle("-fx-text-fill: #4a7aff; -fx-font-size: 20px;");
                break;
            case "PokéTop":
                rankL.setStyle("-fx-text-fill: #73dcff; -fx-font-size: 20px;");
                break;
            case "Inconnus":
                rankL.setText("Dresseur");
                rankL.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
                break;
        }

        profilPanel.getChildren().addAll(skin, pseudoT, pseudo, pseudoL, emailT, email, emailL, rankT, rank, rankL, moneyT, money, moneyL);
        this.layout.getChildren().add(profilPanel);
    }

    private void showTopBar(){
        GridPane background  = new GridPane();
        GridPane.setVgrow(background, Priority.ALWAYS);
        GridPane.setHgrow(background, Priority.ALWAYS);
        background.setStyle("-fx-background-color: #042f59; -fx-opacity: 30%");
        Label accueilLabel = new Label("ACCUEIL");
        accueilLabel.setStyle("-fx-text-fill: rgb(200,200,200); -fx-font-size: 14px;");
        accueilLabel.setMinSize(70,25);
        accueilLabel.setMaxSize(70,25);
        accueilLabel.setTranslateX(30);
        accueilLabel.setOnMouseEntered(e-> {this.layout.setCursor(Cursor.HAND); accueilLabel.setStyle("-fx-text-fill: rgb(5,179,242); -fx-font-size: 14px;");});
        accueilLabel.setOnMouseExited(e-> {this.layout.setCursor(Cursor.DEFAULT); accueilLabel.setStyle("-fx-text-fill: rgb(200,200,200); -fx-font-size: 14px;");});
        accueilLabel.setOnMouseClicked(e-> this.panelManager.showPanel(new PanelHome(this.panelLogin)));
        Label profileLabel = new Label("PROFIL");
        profileLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        profileLabel.setMinSize(70,25);
        profileLabel.setMaxSize(70,25);
        profileLabel.setTranslateX(108);
        profileLabel.setOnMouseEntered(e-> {this.layout.setCursor(Cursor.HAND); profileLabel.setStyle("-fx-text-fill: rgb(5,179,242); -fx-font-size: 14px;");});
        profileLabel.setOnMouseExited(e-> {this.layout.setCursor(Cursor.DEFAULT); profileLabel.setStyle("-fx-text-fill: rgb(200,200,200); -fx-font-size: 14px;");});

        this.panelManager.getTopPanel().getTopBar().getChildren().addAll(accueilLabel,profileLabel);
    }
}
