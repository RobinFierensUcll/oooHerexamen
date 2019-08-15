package view.panels;

import controller.Controller;
import controller.factories.DbFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.db.DbArtikels;
import model.db.DbRekening;
import model.domain.Artikel;
import model.domain.Observer;
import model.domain.ObserverActivator;
import model.domain.Rekening;

import java.util.ArrayList;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class KassaPane extends GridPane implements ObserverActivator {
    private TableView table;
    private Button btnAdd, btnCancel;
    private TextField artikelCodeField;
    private Controller service = new Controller();
    private ArrayList<Observer> observers = new ArrayList<>();

    public KassaPane() {
        setTable();
    }

    public void setTable() {
        this.setPrefHeight(150);
        this.setPrefWidth(300);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Artikel code:"), 0, 1, 1, 1);
        artikelCodeField = new TextField();
        this.add(artikelCodeField, 1, 1, 1, 1);
        btnAdd = new Button("Add");
        btnAdd.isDefaultButton();
        btnAdd.setText("Add");
        btnAdd.setOnAction(new Save());
        add(btnAdd, 2, 1, 2, 1);


        table = new TableView<>();
        table.setPrefWidth(REMAINING);

        setTable(service.getDbFactory());

        TableColumn omschrijvingCol = new TableColumn<>("Omschrijving");
        omschrijvingCol.setCellValueFactory(new PropertyValueFactory("omschrijving"));
        table.getColumns().add(omschrijvingCol);

        TableColumn prijsCol = new TableColumn<>("Prijs");
        prijsCol.setCellValueFactory(new PropertyValueFactory("prijs"));
        table.getColumns().add(prijsCol);

        this.add(table, 0, 4, 5, 6);

        setDoubleClickAction(MOUSE_CLICKED);



    }

    class Save implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Artikel a =  service.getDbFactory().getArtikelByCode(artikelCodeField.getText());
            service.getDomainFactory().createRekeningFromArtikel(a);
            service.getDbFactory().addArtikelToRekening(service.getDomainFactory().getRekening());
            setTableData(service.getDbFactory());
        }
    }

    class Cancel implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            btnCancel.setOnAction((event) -> {
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
            });
        }
    }

    public void setTable(DbFactory dbFactory) {
        table.getItems().clear();
        for (Rekening r : dbFactory.getListKassa()) {
            table.getItems().addAll(r);
        }


    }

    public void setDoubleClickAction(EventType<MouseEvent> e) {
        table.setRowFactory(tv -> {
            TableRow<Rekening> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Rekening artikel = row.getItem();
                    service.getDbFactory().removeArtikelFromRekening(artikel);
                    setTableData(service.getDbFactory());
                    setTable(service.getDbFactory());
                }
            });
            return row;
        });
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void setTableData(Object o) {
        for (Observer x : this.observers) {
            x.update(o);
        }
        DbFactory dbFactory = (DbFactory) o;
        setTable(dbFactory);
    }
}
