package fr.deltadesnoc;

import fr.arinonia.arilibfx.AriLibFX;
import javafx.application.Application;
import javafx.stage.Stage;

public class FxApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(AriLibFX.loadImage("logo"));
        new PCLauncher().init(stage);

    }

}
