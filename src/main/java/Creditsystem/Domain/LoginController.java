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
    public ImageView goBack;
    public PasswordField passwordField;
    public TextField txtName, txtEmail, txtPhone, txtCompany, txtUsername, txtForgotPhone, txtForgotEmail, txtForgotName;
    public Label receipt, lblWrong, infoLabel, statusLabel;
    public CheckBox acceptanceCheck;

    StageChange stageChange = new StageChange();
    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
    private static int loggedInID;
    private Mail mail = new Mail();

    public void handleForgotLogin(ActionEvent event)
    {
        try
        {
            stageChange.openPopup(event, "ForgotLogin.fxml", "Glemt login", 522, 633);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleSendForgotInfo(ActionEvent event)
    {
        if (!txtForgotEmail.getText().isEmpty() && !txtForgotPhone.getText().isEmpty() && !txtForgotName.getText().isEmpty())
        {
            mail.receiveEmail("Glemt Login", "Bruger har glemt login!" + "\n" + "Navn: " + txtForgotName.getText() + "\n" + "Telefon: " + txtForgotPhone.getText() + "\n" + "Email: " + txtForgotEmail.getText());
            infoLabel.setText("Du modtager en mail med nyt" + "\n" + "brugernavn og adgangskode");
            statusLabel.setText("Henvendelse sendt");
            txtForgotEmail.clear();
            txtForgotPhone.clear();
            txtForgotName.clear();
        } else
        {
            statusLabel.setText("Udfyld venligst alle felter");
        }
    }

    public void handleLogin(ActionEvent event)
    {
        String username = txtUsername.getText().toLowerCase();
        String password = passwordField.getText();
        if (username != null && password != null)
        {
            if (persistenceHandler.logIn(username, password))
            {
                loggedInID = persistenceHandler.getAccountID(username);

                if (persistenceHandler.checkAuthorization(username))
                {
                    try
                    {
                        new StageChange().openNewWindow(event, "AdminFrontPage.fxml", "Systemadministrator forside");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                } else
                {
                    try
                    {
                        new StageChange().openNewWindow(event, "ProducerFrontPage.fxml", "Producer forside");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            } else
            {
                lblWrong.setText("Forkert brugernavn eller adgangskode");
            }
        }
    }

    public void handleRegisterLogin(ActionEvent event)
    {
        try
        {
            stageChange.openPopup(event, "RequestLogin.fxml", "Ansøg om login", 522, 633);
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

    public static int getLoggedInID()
    {
        return loggedInID;
    }

    public void handleRequestButton(ActionEvent event)
    {
        String name = txtName.getText();
        String company = txtCompany.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        if (!name.trim().isEmpty() & !company.trim().isEmpty() & !email.trim().isEmpty() & !phone.trim().isEmpty())
        {
            if (acceptanceCheck.isSelected())
            {

                mail.receiveEmail("Ny konto hos krediteringssystemet", "navn: " + name + "\n" + "firma: " + company + "\n" + "email: " + email + "\n" + "telefon: " + phone);
                receipt.setText("Tak for din ansøgning. Brugernavn og adgangskode modtager du på mail når din konto er oprettet");
                txtName.clear();
                txtCompany.clear();
                txtEmail.clear();
                txtPhone.clear();
            } else
            {
                receipt.setText("Accepter venligst betingelserne.");

            }
        } else
        {

            receipt.setText("Udfyld alle felter");
        }
    }

    public void goBack(ActionEvent event)
    {
        stageChange.closePopup(event);
    }
}
