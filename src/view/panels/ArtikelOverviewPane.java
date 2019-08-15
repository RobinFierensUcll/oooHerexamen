package view.panels;

import controller.Controller;
import controller.factories.DbFactory;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.db.DbArtikels;
import model.domain.Artikel;
import model.domain.Observer;


public class ArtikelOverviewPane extends GridPane implements Observer {
    private TableView table;
    private Controller service = new Controller();

    public ArtikelOverviewPane() {
        setTable();
    }

    public void setTable() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Artikels:"), 0, 0, 1, 1);
        table = new TableView<>();
        table.setPrefWidth(REMAINING);

        setTableData(service.getDbFactory());

        TableColumn codeCol = new TableColumn<>("Code");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        table.getColumns().add(codeCol);
        TableColumn groepCol = new TableColumn<>("Groep");
        groepCol.setCellValueFactory(new PropertyValueFactory("groep"));
        table.getColumns().add(groepCol);
        TableColumn omschrijvingCol = new TableColumn<>("Omschrijving");
        omschrijvingCol.setCellValueFactory(new PropertyValueFactory("omschrijving"));
        table.getColumns().add(omschrijvingCol);
        TableColumn prijsCol = new TableColumn<>("Prijs");
        prijsCol.setCellValueFactory(new PropertyValueFactory("prijs"));
        table.getColumns().add(prijsCol);
        TableColumn voorraadCol = new TableColumn<>("Voorraad");
        voorraadCol.setCellValueFactory(new PropertyValueFactory("voorraad"));
        table.getColumns().add(voorraadCol);
        this.add(table, 0, 1, 2, 6);
    }

    public void setTableData(DbFactory dbFactory) {
        table.getItems().clear();
        for (Artikel a : dbFactory.getArtikelsAlphabetic()) {
            table.getItems().addAll(a);
        }
    }

    @Override
    public void update(Object o) {
        DbFactory dbFactory = (DbFactory) o;
        this.setTableData(dbFactory);
    }
}