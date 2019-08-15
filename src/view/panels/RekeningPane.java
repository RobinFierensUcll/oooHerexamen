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
import model.db.DbRekening;
import model.domain.Observer;
import model.domain.Rekening;

public class RekeningPane extends GridPane implements Observer {
    private TableView table;
    private Button btnNew;
    private Controller service = new Controller();
    private Label rekeningTotaal = new Label("");

    public RekeningPane() {
        setTable();
    }

    public void setTable() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(rekeningTotaal, 0, 0, 1, 1);
        rekeningTotaal = new Label();
        table = new TableView<>();
        table.setPrefWidth(REMAINING);

        setTableData(service.getDbFactory());

        TableColumn omschrijvingCol = new TableColumn<>("Omschrijving");
        omschrijvingCol.setCellValueFactory(new PropertyValueFactory("omschrijving"));
        table.getColumns().add(omschrijvingCol);

        TableColumn prijsCol = new TableColumn<>("Prijs per stuk");
        prijsCol.setCellValueFactory(new PropertyValueFactory("prijs"));
        table.getColumns().add(prijsCol);

        TableColumn aantalCol = new TableColumn<>("Aantal");
        aantalCol.setCellValueFactory(new PropertyValueFactory("aantal"));
        table.getColumns().add(aantalCol);

        TableColumn totPrijsCol = new TableColumn<>("Totaal prijs");
        totPrijsCol.setCellValueFactory(new PropertyValueFactory("totPrijs"));
        table.getColumns().add(totPrijsCol);

        this.add(table, 0, 1, 2, 6);

    }

    public void setTableData(DbFactory dbFactory) {
        table.getItems().clear();
        for (Rekening rekening : dbFactory.getRekening()) {
            table.getItems().addAll(rekening);
        }
        this.getChildren().remove(rekeningTotaal);
        rekeningTotaal.setText("Totaal prijs " + dbFactory.getTotaalPrijsKassa());
        this.add(rekeningTotaal, 0, 0, 1, 1);
    }

    @Override
    public void update(Object o) {
        this.setTableData((DbFactory) o);
    }
}
