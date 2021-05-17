package Creditsystem.Domain;

import Creditsystem.Data.PersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.*;


public class LoginController
{

    public Hyperlink registerLogin;
    public Hyperlink forgotLogin;
    public Button btnLogin;
    public ImageView goBack;
    public TextField txtUsername;
    public PasswordField passwordField;
    private static int loggedInID;

    StageChange stageChange = new StageChange();
    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();


    public void handleForgotLogin(ActionEvent event)
    {
        System.out.println("handleForgotLogin");
    }

    public void handleLogin(ActionEvent event)
    {
        String username = txtUsername.getText();
        String password = passwordField.getText();
        if (username != null && password != null)
        {
            if (persistenceHandler.logIn(username, password))
            {
                loggedInID = persistenceHandler.getID(username);

                if (persistenceHandler.checkAuthorization(username))
                {
                    try
                    {
                        new StageChange().openNewWindow(event, "AdminPage.fxml", "Systemadministrator forside");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                } else
                {
                    try
                    {
                        new StageChange().openNewWindow(event, "producerPage.fxml", "Producer forside");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            } else
            {
                System.out.println("try again");
            }
        }
    }


    public void handleRegisterLogin(ActionEvent event)
    {
        try
        {
            stageChange.openPopup(event, "producerPage.fxml", "Opret nyt login");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void handleBackButton(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "FrontPage.fxml", "Krediteringsforside");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public int getLoggedInID()
    {
        return loggedInID;
    }

}
