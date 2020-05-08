package fr.deltadesnoc;

import fr.arinonia.arilibfx.AriLibFX;
import fr.arinonia.arilibfx.utils.AriLogger;
import javafx.application.Application;

import javax.swing.*;

public class Main {

    public static AriLogger logger;

    public static void main(String[] args) {

        AriLibFX.setResourcePath("/fr/deltadesnoc/resources/image/");

        logger = new AriLogger("PokeCraft");

        try {
            Class.forName("javafx.application.Application");
            Application.launch(FxApplication.class, args);
        }catch (ClassNotFoundException e){
            logger.warn("JavaFx not found :cry:");
            JOptionPane.showMessageDialog(null, "Une erreur avec Java à été détectée.\n" + e.getMessage() + "\nNot Found", "Erreur Java", JOptionPane.ERROR_MESSAGE);
        }

    }

}
