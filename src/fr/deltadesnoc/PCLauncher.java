package fr.deltadesnoc;

import fr.deltadesnoc.ui.PanelManager;
import fr.deltadesnoc.ui.panels.PanelLogin;
import javafx.stage.Stage;

public class PCLauncher {

    private PanelManager panelManager;

    public void init(Stage stage){

        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
        this.panelManager.showPanel(new PanelLogin());

    }

}
