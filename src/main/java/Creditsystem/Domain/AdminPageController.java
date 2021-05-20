package Creditsystem.Domain;

import Creditsystem.Data.PersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminPageController
{
    public TextField txtCreateName, txtName, txtEmail, txtPhone, txtCompany;
    public PasswordField pwrdPassword;
    public PasswordField pswrdReEnterPassword;
    public CheckBox adminCheck;
    public TableView tblUsers;
    private TableColumn usernameColumn = new TableColumn();
    private TableColumn isAdminColumn = new TableColumn();
    public Button btnDeleteUser;
    public ListView unreleasedList, accountList;
    private static Production production = null;
    private static ArrayList<CreditJoin> credits = new ArrayList<>();


    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    StageChange stageChange = new StageChange();
    //Account account;

    public static ArrayList<CreditJoin> getCredits()
    {
        return credits;
    }

    public void initialize()
    {
        //initializeColumns();
/*
        for (Production production : persistenceHandler.getUnreleasedProductions())
        {
            unreleasedList.getItems().add(production.getTitle());
        }
        for (Account account : persistenceHandler.getUsers())
        {
            tblUsers.getItems().add(account);
        }
*/
    }
    private void initializeColumns()
    {
        usernameColumn.setText("Navn");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setMinWidth(10);
        usernameColumn.setPrefWidth(290);
        //usernameColumn.setMaxWidth(5000);

        isAdminColumn.setText("Er admin");
        //isAdminColumn.setCellValueFactory(new PropertyValueFactory<>("isadmin"));
        isAdminColumn.setMinWidth(10);
        isAdminColumn.setPrefWidth(190);
        //isAdminColumn.setMaxWidth(5000);

        tblUsers.getColumns().addAll(usernameColumn,isAdminColumn);
    }
    

    public void createNewUser(ActionEvent event)
    {
        String username = txtCreateName.getText().toLowerCase();
        String password = pwrdPassword.getText();
        String reEnter = pswrdReEnterPassword.getText();
        if (username != "" && password != "" && reEnter != "")
        {
            if (password.equals(reEnter))
            {
                if (!persistenceHandler.checkUsername(username))
                {
                    if (adminCheck.isSelected())
                    {
                        Account account = new Account(username, password, adminCheck.isSelected());
                        persistenceHandler.createAdminAccount(account);

                        txtCreateName.clear();
                        pwrdPassword.clear();
                        pswrdReEnterPassword.clear();
                        adminCheck.setSelected(false);
                    } else
                    {
                        ProducerAccount producerAccount = new ProducerAccount(username, password, false, txtName.getText(), txtEmail.getText(), Integer.valueOf(txtPhone.getText()), txtCompany.getText());
                        persistenceHandler.createProducerAccount(producerAccount);
                        txtCreateName.clear();
                        pwrdPassword.clear();
                        pswrdReEnterPassword.clear();
                        txtName.clear();
                        txtEmail.clear();
                        txtPhone.clear();
                        txtCompany.clear();
                    }
                } else
                {
                    System.out.println("username already exists!");
                }

            } else
            {
                System.out.println("Passwords are not the same");
            }
        } else
        {
            System.out.println("You must fill out all the fields");
        }
    }

    public void DeleteUser(ActionEvent event)
    {
        Object obj = tblUsers.getSelectionModel().getSelectedItem();
        tblUsers.getItems().remove(obj);
        String data = (String) usernameColumn.getCellObservableValue(obj).getValue();
        for (int i = 0; i < persistenceHandler.getUsers().size(); i++)
        {
            if (data.equals(persistenceHandler.getUsers().get(i).getUsername()))
            {
                persistenceHandler.deleteUser(data);
                System.out.println("User deleted");
            }
        }

    }

    public void viewProduction(ActionEvent event)
    {
        String data = (String) unreleasedList.getSelectionModel().getSelectedItem();
        for (int i = 0; i < persistenceHandler.getUnreleasedProductions().size(); i++)
        {
            if (data.equals(persistenceHandler.getUnreleasedProductions().get(i).getTitle()))
            {
                production = persistenceHandler.getProductionTitle(data);
                credits = persistenceHandler.getCredits(data);
                try
                {
                    stageChange.openNewWindow(event, "AdminCreditInformation.fxml", "Informations side");
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getHighligtedProduction(ActionEvent event)
    {
        String data = (String) unreleasedList.getSelectionModel().getSelectedItem();
        for (int i = 0; i < persistenceHandler.getUnreleasedProductions().size(); i++)
        {
            if (data.equals(persistenceHandler.getUnreleasedProductions().get(i).getTitle()))
            {
                production = persistenceHandler.getProductionTitle(data);
            }
        }

    }
    public void removeFromList(){
        List<Integer> selectedItemsCopy = new ArrayList<>(unreleasedList.getSelectionModel().getSelectedItems());
        unreleasedList.getItems().removeAll(selectedItemsCopy);
    }

    public void releaseProduction(ActionEvent event)
    {
        getHighligtedProduction(event);
        removeFromList();
        persistenceHandler.releaseProduction(getProduction());
        Mail mail = new Mail();
        mail.sendEmail(persistenceHandler.getProducerAccount(getProduction().getCreatedby()).getEmail(), getProduction().getTitle() + " er nu udgivet","" +
                "Din produktion for " + getProduction().getTitle() + " er nu udgivet. Se den inde på Krediteringssystemet.");
        persistenceHandler.getProducerAccount(2);

    }

    public static Production getProduction()
    {
        return production;
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
    public void handleDeleteAccount(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "DeleteAccount.fxml", "Slet bruger");
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

    public void handleProgramValidation(ActionEvent event) {
        try {
            stageChange.openNewWindow(event, "ProgramValidation.fxml", "Offentliggør Produktion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteAccountPopUp(ActionEvent event)
    {
        try
        {
            stageChange.openPopup(event, "DeleteAccountPopUp.fxml", "Slet bruger",361,171);
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
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
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
