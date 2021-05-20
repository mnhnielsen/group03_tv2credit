package Creditsystem.Domain;

import Creditsystem.Data.PersistenceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FrontPageController {
    //Javafx attributes
    public TextField txtSearch;
    public TableView tblCredits;
    public Button btnOpenCredit;


    private StageChange stageChange = new StageChange();
    private TableColumn titleColumn = new TableColumn();

    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    ArrayList<Production> lists = persistenceHandler.getProductions();
    static ArrayList<CreditJoin> credits = new ArrayList<>();

    static Production production = null;

    public void initialize() {
        initializeColumns();

        for (Production pr : lists) {
            tblCredits.getItems().add(pr);
        }
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->
                tblCredits.setItems(filterList(lists, newValue.toLowerCase()))
        );
    }

    private void initializeColumns() {
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setMinWidth(10);
        titleColumn.setPrefWidth(1170);
        titleColumn.setMaxWidth(5000);

        TableColumn year = new TableColumn("År");
        year.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        year.setMinWidth(10);
        year.setPrefWidth(192);
        year.setMaxWidth(5000);

        tblCredits.getColumns().addAll(titleColumn, year);
    }

    public void handleLogin(ActionEvent event) {
        try {
            stageChange.openNewWindow(event, "LoginPage.fxml", "Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProduction(ActionEvent event) {
        if (tblCredits.getSelectionModel().getSelectedItem() != null) {
            Object obj = tblCredits.getSelectionModel().getSelectedItem();
            String data = (String) titleColumn.getCellObservableValue(obj).getValue();
            for (int i = 0; i < lists.size(); i++) {
                if (data == lists.get(i).getTitle()) {
                    production = persistenceHandler.getProductionTitle(data);
                    credits = persistenceHandler.getCredits(data);
                    try {
                        stageChange.openNewWindow(event, "CreditInfomationPage.fxml", "Informations side");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Denne metoder konverterer teksten i søgefeltet til lowercase.
    private boolean searchFindsProduction(Production production, String searchText) {
        return (production.getTitle().toLowerCase().contains(searchText.toLowerCase())) ||
                (production.getDescription().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(production.getReleaseYear()).toString().equals(searchText.toLowerCase());
    }

    //Søgefunktion
    private ObservableList<Production> filterList(List<Production> list, String searchText) {
        List<Production> filteredList = new ArrayList<>();
        for (Production production : list) {
            if (searchFindsProduction(production, searchText)) {
                filteredList.add(production);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    public static Production getProduction() {
        return production;
    }

    public static ArrayList<CreditJoin> getCredits() {
        return credits;
    }
}
