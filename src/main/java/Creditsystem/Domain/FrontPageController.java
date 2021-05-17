package Creditsystem.Domain;

import Creditsystem.Data.PersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class FrontPageController
{
    //Javafx attributes
    public TextField txtSearch;
    public TableView tblCredits;
    public Button btnOpenCredit;


    private StageChange stageChange = new StageChange();
    private TableColumn titleColumn = new TableColumn();

    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    ArrayList<Production> lists = persistenceHandler.getProductions();

    static Production production = null;

    public void initialize()
    {
        initializeColumns();

        for (Production pr : lists)
        {
            tblCredits.getItems().add(pr);
        }
    }

    private void initializeColumns()
    {
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setMinWidth(10);
        titleColumn.setPrefWidth(192);
        titleColumn.setMaxWidth(5000);

        tblCredits.getColumns().addAll(titleColumn);
    }


    public void handleLogin(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "LoginPage.fxml", "Login");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showProduction(ActionEvent event)
    {
        Object obj = tblCredits.getSelectionModel().getSelectedItem();
        String data = (String) titleColumn.getCellObservableValue(obj).getValue();
        for (int i = 0; i < lists.size(); i++)
        {
            if (data == lists.get(i).getTitle())
            {
                production = persistenceHandler.getProductionTitle(data);
                try
                {
                    stageChange.openNewWindow(event, "CreditInfomationPage.fxml", "Informations side");
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Production getProduction()
    {
        return production;
    }
}
