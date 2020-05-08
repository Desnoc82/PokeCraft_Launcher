package fr.deltadesnoc.ui.panel;

import fr.deltadesnoc.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {

    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();

}
