package Creditsystem.Domain;

import Creditsystem.Data.PersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class LoginController
{
    public Hyperlink registerLogin;
    public Hyperlink forgotLogin;
    public Button btnLogin;
    public Button btnRequest;
    public ImageView goBack;
    public TextField txtUsername;
    public PasswordField passwordField;
    private static int loggedInID;
    public TextField txtName, txtEmail, txtPhone, txtCompany;
    public Label receipt, lblWrong;

    StageChange stageChange = new StageChange();
    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    public void handleForgotLogin(ActionEvent event)
    {
        try
        {
            stageChange.openPopup(event, "ForgotPassword.fxml", "Glemt login", 368,191);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
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
                lblWrong.setText("Fokert brugernavn eller adgangskode");

            }
        }
    }

    public void handleRegisterLogin(ActionEvent event)
    {
        try
        {
            stageChange.openPopup(event, "RequestLogin.fxml", "Ansøg om login", 368, 372);
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

    public void handleRequestButton(ActionEvent event)
    {
        String name = txtName.getText();
        String company = txtCompany.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        if (name.trim() != null & company.trim() != null & email.trim() != null & phone.trim() != null)
        {
            Mail mail = new Mail();
            mail.receiveEmail("Ny konto hos krediteringssystemet", "navn: " + name + "\n" + "firma: " + company + "\n" + "email: " + email + "\n" + "telefon: " + phone);
            receipt.setText("Tak for din ansøgning. Brugernavn og adgangskode modtager du på mail når din konto er oprettet");
            txtName.clear();
            txtCompany.clear();
            txtEmail.clear();
            txtPhone.clear();
        } else
        {
            //Dialog boks skal tilføjes
            System.out.println("Udfyld venligst alle felter");
        }
    }

    public void goBack(ActionEvent event)
    {
        stageChange.closePopup(event);
    }
}
