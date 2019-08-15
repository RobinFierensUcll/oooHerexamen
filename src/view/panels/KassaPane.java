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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Label error = new Label("");

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
        artikelCodeField.setOnKeyPressed(new Save());
        table = new TableView<>();
        table.setPrefWidth(REMAINING);
        this.add(error, 0, 0, 1, 1);
        error = new Label();
        error.setVisible(false);

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

    class Save implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    Artikel a = service.getDbFactory().getArtikelByCode(artikelCodeField.getText());
                    Rekening r = service.getDomainFactory().createRekeningFromArtikel(a);
                    service.getDbFactory().addArtikelToRekening(r);
                    setTableData(service.getDbFactory());
                }catch (Exception exception){
                    error.setText(exception.getMessage());
                    error.setVisible(true);
                }
            }
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
