package Creditsystem.Domain.Controllers.Admin;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.Model.Account;
import Creditsystem.Domain.Helpers.StageChange;
import Creditsystem.Domain.IPersistenceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AdminUserList implements Initializable
{
    public TableView tblUsers;
    public TextField txtSearch;
    private TableColumn usernameColumn = new TableColumn();
    private TableColumn isAdminColumn = new TableColumn();

    private IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
    private StageChange stageChange = new StageChange();

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initializeColumns();

        for (Account account : persistenceHandler.getUsers())
        {
            tblUsers.getItems().add(account);
        }
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->
                tblUsers.setItems(filterList(persistenceHandler.getUsers(), newValue.toLowerCase()))
        );
    }

    private void initializeColumns()
    {
        usernameColumn.setText("Navn");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setMinWidth(10);
        usernameColumn.setPrefWidth(290);
        //usernameColumn.setMaxWidth(5000);

        isAdminColumn.setText("Er admin");
        isAdminColumn.setCellValueFactory(new PropertyValueFactory<>("isadmin"));
        isAdminColumn.setMinWidth(10);
        isAdminColumn.setPrefWidth(190);
        //isAdminColumn.setMaxWidth(5000);

        tblUsers.getColumns().addAll(usernameColumn, isAdminColumn);
    }
    private boolean searchFindsProduction(Account account, String searchText) {
        return
                (account.getUsername().toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Account> filterList(List<Account> list, String searchText) {
        List<Account> filteredList = new ArrayList<>();
        for (Account account : list) {
            if (searchFindsProduction(account, searchText)) {
                filteredList.add(account);
            }
        }
        return FXCollections.observableList(filteredList);
    }
    public void deleteUser(ActionEvent event) {

        if (tblUsers.getSelectionModel().getSelectedItem() != null) {
            Object obj = tblUsers.getSelectionModel().getSelectedItem();
            tblUsers.getItems().remove(obj);
            String data = (String) usernameColumn.getCellObservableValue(obj).getValue();
            for (int i = 0; i < persistenceHandler.getUsers().size(); i++) {
                if (data.equals(persistenceHandler.getUsers().get(i).getUsername())) {
                    persistenceHandler.deleteUser(data);
                    System.out.println("User deleted");
                }
            }
        }
    }
    public void handleMyAdminCreateUser(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "AdminCreateUser.fxml", "Opret bruger");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handlePublications(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "Publications.fxml", "Publikations anmodninger");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

     public void handleAdminProfile(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "AdminProfile.fxml", "Min profil");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void handleAdminLogOut(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "FrontPage.fxml", "Login");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
