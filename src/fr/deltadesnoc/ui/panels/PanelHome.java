package fr.deltadesnoc.ui.panels;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arilibfx.ui.panel.IPanel;
import fr.deltadesnoc.Main;
import fr.deltadesnoc.ui.PanelManager;
import fr.deltadesnoc.ui.components.ABigDownloadBar;
import fr.deltadesnoc.ui.components.ANews;
import fr.deltadesnoc.ui.components.ASmallDownloadBar;
import fr.deltadesnoc.ui.panel.Panel;
import fr.deltadesnoc.ui.ram.RamManager;
import fr.deltadesnoc.ui.ram.RamPanel;
import fr.deltadesnoc.utils.FxAlert;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.Application;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.supdate.exception.BadServerResponseException;
import fr.theshark34.supdate.exception.BadServerVersionException;
import fr.theshark34.supdate.exception.ServerDisabledException;
import fr.theshark34.supdate.exception.ServerMissingSomethingException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import re.alwyn974.openlauncherlib.LaunchException;
import re.alwyn974.openlauncherlib.external.ExternalLaunchProfile;
import re.alwyn974.openlauncherlib.external.ExternalLauncher;
import re.alwyn974.openlauncherlib.minecraft.*;
import re.alwyn974.openlauncherlib.util.ProcessLogManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PanelHome extends Panel {

    private GridPane centerPane = new GridPane();
    private PanelLogin panelLogin;
    private ABigDownloadBar aBigDownloadBar;
    private ASmallDownloadBar aSmallDownloadBar;
    private Label profileLabel;

    private final static boolean COMPATIBLE = System.getProperty("sun.arch.data.model").equals("64");
    public static final GameVersion EM_VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
    public static final GameInfos EM_INFOS = new GameInfos("pokecraft", EM_VERSION, new GameTweak[] { GameTweak.FORGE });
    public static final File DIR = EM_INFOS.getGameDir();
    private static AuthInfos authInfos;
    private static File ramText = new File(DIR, "ram.properties");

    public PanelHome(PanelLogin panelLogin) { this.panelLogin = panelLogin; }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        showTopBar();

        ColumnConstraints menuPaneConstraint = new ColumnConstraints();
        menuPaneConstraint.setHalignment(HPos.LEFT);
        menuPaneConstraint.setMinWidth(300);
        menuPaneConstraint.setMaxWidth(300);
        this.layout.getColumnConstraints().addAll(menuPaneConstraint, new ColumnConstraints());

        GridPane leftBarPane = new GridPane();
        GridPane.setVgrow(leftBarPane, Priority.ALWAYS);
        GridPane.setHgrow(leftBarPane, Priority.ALWAYS);

        Separator rightSeparator = new Separator();
        GridPane.setVgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHalignment(rightSeparator, HPos.RIGHT);
        rightSeparator.setOrientation(Orientation.VERTICAL);
        rightSeparator.setTranslateY(1);
        rightSeparator.setTranslateX(4);
        rightSeparator.setMinWidth(2);
        rightSeparator.setMaxWidth(2);
        rightSeparator.setOpacity(0.30);

        GridPane bottomGridPane = new GridPane();
        GridPane.setVgrow(bottomGridPane, Priority.ALWAYS);
        GridPane.setHgrow(bottomGridPane, Priority.ALWAYS);
        GridPane.setHalignment(bottomGridPane, HPos.LEFT);
        GridPane.setValignment(bottomGridPane, VPos.TOP);
        bottomGridPane.setTranslateY(30);
        bottomGridPane.setMinHeight(40);
        bottomGridPane.setMaxHeight(40);
        bottomGridPane.setMinWidth(300);
        bottomGridPane.setMaxWidth(300);
        showLeftBar(bottomGridPane);
        leftBarPane.getChildren().addAll(rightSeparator, bottomGridPane);

        this.layout.add(leftBarPane,0,0);
        this.layout.add(this.centerPane,1,0);
        GridPane.setHgrow(this.centerPane, Priority.ALWAYS);
        GridPane.setVgrow(this.centerPane, Priority.ALWAYS);
        ScrollPane scrollPane = new ScrollPane();
        GridPane.setHgrow(scrollPane, Priority.ALWAYS);
        GridPane.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.getStylesheets().add(Main.class.getResource("/fr/deltadesnoc/resources/css/scrollbar.css").toExternalForm());


        VBox vBox = new VBox();
        GridPane.setHgrow(vBox, Priority.ALWAYS);
        GridPane.setVgrow(vBox, Priority.ALWAYS);
        vBox.setMinHeight(200);
        vBox.setMinWidth(900);
        vBox.setMaxWidth(900);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateX(30);
        GridPane topPane = new GridPane();
        GridPane.setVgrow(topPane, Priority.ALWAYS);
        GridPane.setHgrow(topPane, Priority.ALWAYS);
        GridPane.setValignment(topPane, VPos.TOP);
        topPane.setMaxWidth(880);
        topPane.setMinWidth(880);
        topPane.setMaxHeight(340);
        topPane.setMinHeight(340);
        addTopPanel(topPane);


        GridPane newsPane = new GridPane();
        GridPane.setVgrow(newsPane, Priority.ALWAYS);
        GridPane.setHgrow(newsPane, Priority.ALWAYS);
        GridPane.setValignment(newsPane, VPos.BOTTOM);
        newsPane.setMaxWidth(880);
        newsPane.setMinWidth(880);
        newsPane.setMaxHeight(300);
        newsPane.setMinHeight(300);
        newsPane.setTranslateY(50);
        addNewsPanel(newsPane);
        GridPane  footerPane = new GridPane();
        GridPane.setVgrow(footerPane, Priority.ALWAYS);
        GridPane.setHgrow(footerPane, Priority.ALWAYS);
        GridPane.setValignment(footerPane, VPos.BOTTOM);
        footerPane.setMaxWidth(880);
        footerPane.setMinWidth(880);
        footerPane.setMaxHeight(280);
        footerPane.setMinHeight(280);
        footerPane.setTranslateY(100);
        showFooterPanel(footerPane);
        this.centerPane.getChildren().addAll(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0,topPane);
        vBox.getChildren().add(1,newsPane);
        vBox.getChildren().add(2,footerPane);


    }

    private void addTopPanel(GridPane pane){
        Label pokeTitle = new Label("PokeCraft");
        GridPane.setVgrow(pokeTitle, Priority.ALWAYS);
        GridPane.setHgrow(pokeTitle, Priority.ALWAYS);
        GridPane.setValignment(pokeTitle, VPos.TOP);
        pokeTitle.setStyle("-fx-font-size: 26px; -fx-text-fill: #fff; -fx-font-weight: bold");
        pokeTitle.setTranslateY(20);
        Label survie = new Label("Survie");
        GridPane.setVgrow(survie, Priority.ALWAYS);
        GridPane.setHgrow(survie, Priority.ALWAYS);
        GridPane.setValignment(survie, VPos.TOP);
        survie.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px; -fx-opacity: 70%");
        survie.setTranslateY(70);

        Label pixelmon = new Label("Pixelmon");
        GridPane.setVgrow(pixelmon, Priority.ALWAYS);
        GridPane.setHgrow(pixelmon, Priority.ALWAYS);
        GridPane.setValignment(pixelmon, VPos.TOP);
        pixelmon.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px; -fx-opacity: 70%");
        pixelmon.setTranslateY(70);
        pixelmon.setTranslateX(80);

        Label rolePlay = new Label("Role Play");
        GridPane.setVgrow(rolePlay, Priority.ALWAYS);
        GridPane.setHgrow(rolePlay, Priority.ALWAYS);
        GridPane.setValignment(rolePlay, VPos.TOP);
        rolePlay.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px; -fx-opacity: 70%");
        rolePlay.setTranslateY(70);
        rolePlay.setTranslateX(160);

        Label desc = new Label("PokéCraft est un nouveau serveur et, le premier serveur sous Pixelmon Génération !\n" +
                "Nous reprenons le GamePlay des jeux Pokémons !\n" +
                "Vous vivrez une aventure formidable : vous récolterez vos badges pour accédez \n" +
                "à la Ligue dans une région entièrement crée de toute pièce ! \n" +
                "Ou alors, vous pourrez commencer votre aventure survie \n" +
                "en créant votre base ou même votre ville ! (Les deux fonctionnent indépendamment l'un de l'autre)\n \n"
        );


        GridPane.setVgrow(desc, Priority.ALWAYS);
        GridPane.setHgrow(desc, Priority.ALWAYS);
        GridPane.setValignment(desc, VPos.TOP);
        desc.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px; -fx-opacity: 70%");
        desc.setTranslateY(130);

        Button installButton = new Button("Installer");
        GridPane.setVgrow(installButton, Priority.ALWAYS);
        GridPane.setHgrow(installButton, Priority.ALWAYS);
        GridPane.setHalignment(installButton, HPos.LEFT);
        GridPane.setValignment(installButton, VPos.TOP);
        installButton.setTranslateY(260);
        installButton.setMinWidth(140);
        installButton.setMaxHeight(40);
        installButton.setStyle("-fx-background-color: #115faa; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: white;");
        installButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        installButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        GridPane.setVgrow(installButton, Priority.ALWAYS);
        GridPane.setHgrow(installButton, Priority.ALWAYS);
        GridPane.setHalignment(installButton, HPos.LEFT);
        GridPane.setValignment(installButton, VPos.TOP);
        installButton.setTranslateY(260);
        installButton.setMinWidth(140);
        installButton.setMaxHeight(40);
        installButton.setStyle("-fx-background-color: #115faa; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: white;");
        installButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        installButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        installButton.setOnMouseClicked(e -> {
            buttonClicked();
        });

        Button settingsButton = new Button();
        GridPane.setVgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHalignment(settingsButton, HPos.LEFT);
        GridPane.setValignment(settingsButton, VPos.TOP);
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Color.rgb(17,95,170));
        settingsButton.setStyle("-fx-background-color: rgba(255,255,255,0.0); -fx-border-color: #115faa; -fx-border-radius: 2px");
        settingsButton.setTranslateX(150);
        settingsButton.setTranslateY(266);
        settingsButton.setMinWidth(26);
        settingsButton.setMaxWidth(26);
        settingsButton.setMinHeight(26);
        settingsButton.setMaxHeight(26);
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        settingsButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        settingsButton.setOnMouseClicked(e-> {
            new RamPanel();
        });
        aBigDownloadBar = new ABigDownloadBar();

        pane.getChildren().addAll(pokeTitle,survie,pixelmon,rolePlay,desc, installButton, settingsButton,aBigDownloadBar);
    }

    private void showLeftBar(GridPane pane){

        Separator blueLeftSeparator = new Separator();
        GridPane.setVgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(blueLeftSeparator, Priority.ALWAYS);
        blueLeftSeparator.setOrientation(Orientation.VERTICAL);
        blueLeftSeparator.setMinWidth(3);
        blueLeftSeparator.setMaxWidth(3);
        blueLeftSeparator.setMinHeight(40);
        blueLeftSeparator.setMaxHeight(40);
        blueLeftSeparator.setStyle("-fx-background-color: rgb(5,179,242); -fx-border-width: 3 3 3 0; -fx-border-color: rgb(5,179,242);");

        ImageView skin = new ImageView("https://minotar.net/avatar/" + panelLogin.getAuthMineweb().getInfo("pseudo") + "/100.png");
        GridPane.setVgrow(skin, Priority.ALWAYS);
        GridPane.setHgrow(skin, Priority.ALWAYS);
        GridPane.setValignment(skin, VPos.CENTER);
        skin.setTranslateX(34);
        skin.setFitWidth(30);
        skin.setFitHeight(30);

        Label pseudoLabel = new Label( panelLogin.getAuthMineweb().getInfo("pseudo"));
        GridPane.setVgrow(pseudoLabel, Priority.ALWAYS);
        GridPane.setHgrow(pseudoLabel, Priority.ALWAYS);
        GridPane.setValignment(pseudoLabel, VPos.CENTER);
        pseudoLabel.setTranslateX(90);
        pseudoLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #fff");

        Separator redLeftSeparator = new Separator();
        GridPane.setVgrow(redLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(redLeftSeparator, Priority.ALWAYS);
        redLeftSeparator.setOrientation(Orientation.VERTICAL);
        redLeftSeparator.setMinWidth(3);
        redLeftSeparator.setMaxWidth(3);
        redLeftSeparator.setMinHeight(40);
        redLeftSeparator.setMaxHeight(40);
        redLeftSeparator.setStyle("-fx-background-color: rgb(242,50,5); -fx-border-width: 3 3 3 0; -fx-border-color: rgb(242,50,5);");
        redLeftSeparator.setTranslateY(blueLeftSeparator.getTranslateY() + 60);

        Image logoImagePokeCraft = new Image(Main.class.getResource("/fr/deltadesnoc/resources/image/logo.png").toExternalForm());
        ImageView imageViewPokeCraft = new ImageView(logoImagePokeCraft);
        GridPane.setVgrow(imageViewPokeCraft, Priority.ALWAYS);
        GridPane.setHgrow(imageViewPokeCraft, Priority.ALWAYS);
        GridPane.setValignment(imageViewPokeCraft, VPos.CENTER);
        imageViewPokeCraft.setTranslateX(34);
        imageViewPokeCraft.setFitWidth(28);
        imageViewPokeCraft.setFitHeight(28);
        imageViewPokeCraft.setTranslateY(skin.getTranslateY() + 60);

        Label pokeLabel = new Label("PokeCraft");
        GridPane.setVgrow(pokeLabel, Priority.ALWAYS);
        GridPane.setHgrow(pokeLabel, Priority.ALWAYS);
        GridPane.setValignment(pokeLabel, VPos.CENTER);
        pokeLabel.setTranslateX(90);
        pokeLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #fff");
        pokeLabel.setTranslateY(pseudoLabel.getTranslateY() + 60);

        aSmallDownloadBar = new ASmallDownloadBar();
        aSmallDownloadBar.setTranslateX(90);
        aSmallDownloadBar.setTranslateY(aSmallDownloadBar.getTranslateY() + 60);

        pane.getChildren().addAll(blueLeftSeparator,skin,pseudoLabel, redLeftSeparator, imageViewPokeCraft, pokeLabel, aSmallDownloadBar);
    }

    private void showFooterPanel(GridPane pane){
        Rectangle background = new Rectangle();
        background.setFill(Color.rgb(22,34,50));
        background.setWidth(880);
        background.setHeight(100);
        background.setTranslateY(80);
        Label configRequise = new Label("CONFIGURATION REQUISE");
        configRequise.setStyle("-fx-text-fill: #accaeb; -fx-font-size: 16px; -fx-font-weight: bold");
        configRequise.setTranslateY(50);
        configRequise.setTranslateX(10);
        Label osRequisLabel = new Label("Système d'exploitation:");
        osRequisLabel.setStyle("-fx-text-fill: #fff; -fx-font-size: 14px;");
        osRequisLabel.setTranslateY(80);
        osRequisLabel.setTranslateX(10);
        Label osRequis = new Label("Windows 7 64 bit");
        osRequis.setStyle("-fx-text-fill: #5d8098; -fx-font-size: 14px;");
        osRequis.setTranslateY(110);
        osRequis.setTranslateX(10);
        Label gpuRequisLabel = new Label("GPU:");
        gpuRequisLabel.setStyle("-fx-text-fill: #fff; -fx-font-size: 14px;");
        gpuRequisLabel.setTranslateY(80);
        gpuRequisLabel.setTranslateX(220);
        Label gpuRequis = new Label("Geforce GT440 / Radeon HD");
        gpuRequis.setStyle("-fx-text-fill: #5d8098; -fx-font-size: 14px;");
        gpuRequis.setTranslateY(110);
        gpuRequis.setTranslateX(220);

        Label configRecommande = new Label("CONFIGURATION RECOMMANDÉE");
        configRecommande.setStyle("-fx-text-fill: #accaeb; -fx-font-size: 16px; -fx-font-weight: bold");
        configRecommande.setTranslateY(50);
        configRecommande.setTranslateX(500);
        Label osRecomLabel = new Label("Système d'exploitation:");
        osRecomLabel.setStyle("-fx-text-fill: #fff; -fx-font-size: 14px;");
        osRecomLabel.setTranslateY(80);
        osRecomLabel.setTranslateX(500);
        Label osRecom = new Label("Windows 10 64 bit");
        osRecom.setStyle("-fx-text-fill: #5d8098; -fx-font-size: 14px;");
        osRecom.setTranslateY(110);
        osRecom.setTranslateX(500);
        Label gpuRecomLabel = new Label("GPU:");
        gpuRecomLabel.setStyle("-fx-text-fill: #fff; -fx-font-size: 14px;");
        gpuRecomLabel.setTranslateY(80);
        gpuRecomLabel.setTranslateX(720);
        Label gpuRecom = new Label("Nvidia GeForce GT720M");
        gpuRecom.setStyle("-fx-text-fill: #5d8098; -fx-font-size: 14px;");
        gpuRecom.setTranslateY(110);
        gpuRecom.setTranslateX(720);
        Separator separator = new Separator();
        separator.setMinHeight(1);
        separator.setMaxHeight(1);
        separator.setTranslateY(15);
        separator.setStyle("-fx-background-color: #2B405B; -fx-border-width: 2 2 2 940; -fx-border-color: #2B405B;");
        pane.getChildren().addAll(background,configRequise, osRequisLabel, osRequis,gpuRequisLabel, gpuRequis,configRecommande,osRecomLabel,osRecom, gpuRecomLabel, gpuRecom,separator);
    }

    private void addNewsPanel(GridPane pane){
        Label newsLabel = new Label("NOUVEAUTÉS");
        GridPane.setVgrow(newsLabel, Priority.ALWAYS);
        GridPane.setHgrow(newsLabel, Priority.ALWAYS);
        GridPane.setHalignment(newsLabel, HPos.LEFT);
        GridPane.setValignment(newsLabel, VPos.TOP);
        newsLabel.setStyle("-fx-text-fill: #fff; -fx-font-size: 16px; -fx-font-weight: bold");

        ANews spawn = new ANews("spawn", "[01/05/2020] Implémentation du nouveau spawn");
        spawn.setTranslateY(-50);

        ANews site = new ANews("site", "[09/05/2020] Ouverture du site web");
        site.setTranslateY(-50);
        site.setTranslateX(310);

        ANews server = new ANews("server", "[09/05/2020] Ouverture du serveur");
        server.setTranslateY(-50);
        server.setTranslateX(620);

        /*Button plusDactu = new Button("Plus d'actualité");
        GridPane.setVgrow(plusDactu, Priority.ALWAYS);
        GridPane.setHgrow(plusDactu, Priority.ALWAYS);
        GridPane.setHalignment(plusDactu, HPos.LEFT);
        GridPane.setValignment(plusDactu, VPos.BOTTOM);
        plusDactu.setStyle("-fx-font-size: 14px; -fx-background-color: rgba(0,0,0,0.0); -fx-text-fill: #416389; -fx-border-color: #416389");
        plusDactu.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        plusDactu.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));*/

        pane.getChildren().addAll(newsLabel, spawn, site, server/*, plusDactu*/);
    }


    private void showTopBar(){
        GridPane background  = new GridPane();
        GridPane.setVgrow(background, Priority.ALWAYS);
        GridPane.setHgrow(background, Priority.ALWAYS);
        background.setStyle("-fx-background-color: #042f59; -fx-opacity: 30%");
        Label accueilLabel = new Label("ACCUEIL");
        accueilLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        accueilLabel.setMinSize(70,25);
        accueilLabel.setMaxSize(70,25);
        accueilLabel.setTranslateX(30);
        accueilLabel.setOnMouseEntered(e-> {this.layout.setCursor(Cursor.HAND); accueilLabel.setStyle("-fx-text-fill: rgb(5,179,242); -fx-font-size: 14px;");});
        accueilLabel.setOnMouseExited(e-> {this.layout.setCursor(Cursor.DEFAULT); accueilLabel.setStyle("-fx-text-fill: rgb(200,200,200); -fx-font-size: 14px;");});
        profileLabel = new Label("PROFIL");
        profileLabel.setStyle("-fx-text-fill: rgb(200,200,200); -fx-font-size: 14px;");
        profileLabel.setMinSize(70,25);
        profileLabel.setMaxSize(70,25);
        profileLabel.setTranslateX(108);
        profileLabel.setOnMouseEntered(e-> {this.layout.setCursor(Cursor.HAND); profileLabel.setStyle("-fx-text-fill: rgb(5,179,242); -fx-font-size: 14px;");});
        profileLabel.setOnMouseExited(e-> {this.layout.setCursor(Cursor.DEFAULT); profileLabel.setStyle("-fx-text-fill: rgb(200,200,200); -fx-font-size: 14px;");});
        profileLabel.setOnMouseClicked(e-> this.panelManager.showPanel(new PanelProfil(this.panelLogin)));
        profileLabel.setOnMouseClicked(e-> this.panelManager.showPanel(new PanelProfil(this.panelLogin)));
        this.panelManager.getTopPanel().getTopBar().getChildren().addAll(accueilLabel,profileLabel);
    }

    public void showPanel(IPanel panel) {
        this.centerPane.getChildren().clear();
        this.centerPane.getChildren().add(panel.getLayout());
        panel.init(this.panelManager);
        panel.onShow();
    }

    public void buttonClicked(){
        profileLabel.setDisable(true);
        final SUpdate su = new SUpdate("https://launcher.pokecraft-fr.fr/supdate/launcher/", this.DIR);
        su.getServerRequester().setRewriteEnabled(true);
        su.addApplication((Application)new FileDeleter());
        Thread t = new Thread(){
            public void run(){
                try {
                    su.start();
                }catch (BadServerResponseException | ServerDisabledException | BadServerVersionException | ServerMissingSomethingException | IOException e) {
                    Platform.runLater(new Runnable(){
                        public void run(){
                            FxAlert alert = new FxAlert(Alert.AlertType.ERROR, "PokeCraft Launcher | Erreur", "Une erreur est survenue", "Si l'erreur perciste veuillez contacter un membre du staff !", e, "");
                            alert.setIcon("logo");
                            alert.setImage("logo", 100, 100);
                            alert.show();
                        }
                    });
                    e.printStackTrace();
                    return;
                }
                try {
                    authInfos = new AuthInfos(panelLogin.getAuthMineweb().getInfo("pseudo")," ","");
                    ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(EM_INFOS, GameFolder.BASIC, authInfos);
                    profile.getVmArgs().addAll(COMPATIBLE ? ramText.exists() ? Integer.parseInt(RamManager.getRam()) > 0 ? Arrays.asList("-Xms1G","-Xmx"+ RamManager.getRam()+"G") : Arrays.asList("-Xms1G","-Xmx2G") : Arrays.asList("-Xms1G","-Xmx2G") : Arrays.asList("-Xms512M","-Xmx1024M"));
                    ExternalLauncher launcher = new ExternalLauncher(profile);
                    Process p = launcher.launch();
                    ProcessLogManager manager = new ProcessLogManager(p.getInputStream(), new File(DIR, "logs.txt"));
                    manager.start();
                    try {
                        Thread.sleep(5000L);
                        System.exit(0);
                        p.waitFor();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                } catch (LaunchException e) {
                    FxAlert alert = new FxAlert(Alert.AlertType.ERROR, "PokeCraft Launcher | Erreur", "Une erreur est survenue", "Si l'erreur perciste veuillez contacter un membre du staff !", (Exception)e, "");
                    alert.setIcon("logo");
                    alert.setImage("logo", 100, 100);
                    alert.show();
                    e.printStackTrace();
                    return;
                }
            }
        };
        t.start();
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            int val;
            int max;
            int valMb;
            int maxMb;
            long lastDownloaded = 0;
            @Override
            public void handle(ActionEvent event) {
                if (BarAPI.getNumberOfFileToDownload() != 0) {
                    this.val = ((int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000L));
                    this.max = ((int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000L));

                    this.valMb = (int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000000L);
                    this.maxMb = ((int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000000L));

                    aBigDownloadBar.setProgress(val, max);
                    aSmallDownloadBar.setProgress(val,max);

                    long downloaded = BarAPI.getNumberOfTotalDownloadedBytes();
                    long inSecond = downloaded - lastDownloaded;
                    lastDownloaded = downloaded;
                    double speed = inSecond / 1000D;

                    aBigDownloadBar.setInfoLabel("Téléchargement: " + this.valMb + " / " + this.maxMb + " MB @ " + speed + "Kb/s");
                }
                if(BarAPI.getNumberOfFileToDownload() == 0){
                    aBigDownloadBar.setInfoLabel("Lancement du jeu ...");
                }
            }
        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

}
