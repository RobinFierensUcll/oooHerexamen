package view.panels;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class KassaView extends BorderPane {

    public KassaView(Pane artikelPane, Pane kassaPane, Pane instellingennPane, Pane logPane) {
        TabPane tabPane = new TabPane();

        Tab kassaTab = new Tab("Kassa", kassaPane);
        Tab artikelTab = new Tab("Artikels", artikelPane);
        Tab instellingenTab = new Tab("Instellingen", instellingennPane);
        Tab logTab = new Tab("Log", logPane);

        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingenTab);
        tabPane.getTabs().add(logTab);

        this.setCenter(tabPane);
    }
}
