package view.panels;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class KlantView extends BorderPane {
    public KlantView(Pane rekeningPane) {
        TabPane tabPane = new TabPane();

        Tab artikelTab = new Tab("Rekening", rekeningPane);

        tabPane.getTabs().add(artikelTab);

        this.setCenter(tabPane);
    }
}
