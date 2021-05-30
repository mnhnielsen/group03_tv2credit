package Creditsystem.Domain.Controllers.Admin;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.Model.Account;
import Creditsystem.Domain.Controllers.Login.LoginController;
import Creditsystem.Domain.Helpers.StageChange;
import Creditsystem.Domain.IPersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminProfile
{
    public Label lblStatusUsername, lblStatusPassword;
    public TextField txtOldUsername, txtNewUsername, txtRepeatNewUsername,
            txtOldPassword, txtNewPassword, txtRepeatNewPassword;

    private StageChange stageChange = new StageChange();
    private IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
    private Account account;

    public void initialize()
    {
        account = persistenceHandler.getAccount(LoginController.getLoggedInID());
        txtOldUsername.setText(account.getUsername());
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

    public void saveNewUsername(ActionEvent event)
    {
        if (!txtNewUsername.getText().isEmpty() && !txtOldUsername.getText().isEmpty() && !txtRepeatNewUsername.getText().isEmpty())
        {
            if (txtRepeatNewUsername.getText().equals(txtNewUsername.getText()))
            {

                if (persistenceHandler.checkUsername(txtNewUsername.getText()))
                {
                    System.out.println("The same");
                    txtNewUsername.setStyle("-fx-border-color: red;");
                    lblStatusUsername.setStyle("-fx-text-fill: red");
                    lblStatusUsername.setText("Brugernavn eksisterer allerede");

                } else
                {
                    System.out.println("Not the same");
                    txtNewUsername.setStyle(null);
                    txtRepeatNewUsername.setStyle(null);
                    lblStatusUsername.setStyle(null);

                    int id = LoginController.getLoggedInID();
                    String username = txtNewUsername.getText();
                    String password = account.getPassword();
                    persistenceHandler.changeAccountUsername(new Account(id, username, password, true));

                    txtRepeatNewUsername.clear();
                    txtNewUsername.clear();
                    txtOldUsername.setText(persistenceHandler.getAccount(LoginController.getLoggedInID()).getUsername());
                    lblStatusUsername.setText("Brugernavn ændret");
                }
            } else
            {
                txtRepeatNewUsername.setStyle("-fx-border-color: red;");
                lblStatusUsername.setStyle("-fx-text-fill: red");
                lblStatusUsername.setText("Det genindtastede brugernavn stemmer ikke");
            }
        }

    }

    public void saveNewPassword(ActionEvent event)
    {
        if (!txtOldPassword.getText().isEmpty() && !txtNewPassword.getText().isEmpty() && !txtRepeatNewPassword.getText().isEmpty())
        {
            if (txtRepeatNewPassword.getText().equals(txtNewPassword.getText()))
            {
                    int id = LoginController.getLoggedInID();
                    String username = account.getUsername();
                    String password = txtNewPassword.getText();
                    persistenceHandler.changeAccountPassword(new Account(id,username,password, true));

                    txtOldPassword.clear();
                    txtRepeatNewPassword.clear();
                    txtNewPassword.clear();
                    txtRepeatNewPassword.setStyle(null);
                    lblStatusPassword.setText("Password ændret");
            } else
            {
                txtRepeatNewPassword.setStyle("-fx-border-color: red;");
                lblStatusPassword.setStyle("-fx-text-fill: red");
                lblStatusPassword.setText("Det genindtastede password stemmer ikke");
            }
        }
    }
}
