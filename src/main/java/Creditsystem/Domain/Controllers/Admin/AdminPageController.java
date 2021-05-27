package Creditsystem.Domain.Controllers.Admin;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.Account;
import Creditsystem.Domain.Helpers.StageChange;
import Creditsystem.Domain.IPersistenceHandler;
import Creditsystem.Domain.Producer;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class AdminPageController
{

    public TextField txtCreateName, txtName, txtEmail, txtPhone, txtCompany;
    public PasswordField pwrdPassword;
    public PasswordField pswrdReEnterPassword;
    public CheckBox adminCheck;
    public Button btnDeleteUser;
    private IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
    private StageChange stageChange = new StageChange();


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
                        Producer producerAccount = new Producer(username, password, false, txtName.getText(), txtEmail.getText(), Integer.valueOf(txtPhone.getText()), txtCompany.getText());
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
