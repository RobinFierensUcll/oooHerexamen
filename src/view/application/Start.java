package view.application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.*;

public class Start {
    private Stage kassaStage = new Stage();
    private Stage klantStage = new Stage();

    public Start(){
        ArtikelOverviewPane artikelOverviewPane = new ArtikelOverviewPane();
        KassaPane kassaPane = new KassaPane();
        RekeningPane rekeningPane = new RekeningPane();
        LogPane logPane = new LogPane();
        InstellingenPane instellingenPane = new InstellingenPane();

        kassaPane.addObserver(rekeningPane);
        kassaPane.addObserver(artikelOverviewPane);

        Group kassaRoot = new Group();
        Scene kassaScene = new Scene(kassaRoot, 750, 400);
        BorderPane kassaBorder = new KassaView(artikelOverviewPane, kassaPane,instellingenPane, logPane);
        kassaBorder.prefHeightProperty().bind(kassaScene.heightProperty());
        kassaBorder.prefWidthProperty().bind(kassaScene.widthProperty());
        kassaRoot.getChildren().add(kassaBorder);
        kassaStage.setTitle("KASSA VIEW");
        kassaStage.setScene(kassaScene);
        kassaStage.sizeToScene();
        kassaStage.show();

        Group klantRoot = new Group();
        Scene klantScene = new Scene(klantRoot, 750, 400);
        BorderPane klantBorder = new KlantView(rekeningPane);
        klantBorder.prefHeightProperty().bind(klantScene.heightProperty());
        klantBorder.prefWidthProperty().bind(klantScene.widthProperty());
        klantRoot.getChildren().add(klantBorder);
        klantStage.setTitle("KLANT VIEW");
        klantStage.setScene(klantScene);
        klantStage.sizeToScene();
        klantStage.show();


    }
}
