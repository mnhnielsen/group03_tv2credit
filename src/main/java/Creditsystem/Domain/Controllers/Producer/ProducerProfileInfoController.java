package Creditsystem.Domain.Controllers.Producer;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.Controllers.Login.LoginController;
import Creditsystem.Domain.Helpers.StageChange;
import Creditsystem.Domain.IPersistenceHandler;
import Creditsystem.Domain.Model.Producer;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProducerProfileInfoController
{

    public TextField txtOldName, txtNewName, txtRepeatNewName,
            txtOldPhone, txtNewPhone, txtRepeatNewPhone, txtOldMail,
            txtNewMail, txtRepeatNewMail, txtOldPassword, txtNewPassword,
            txtRepeatNewPassword;
    public Label lblPhoneStatus, lblNameStatus, lblEmailStatus, lblPasswordStatus;
    private Producer currentProducerAccount;
    private StageChange stageChange = new StageChange();
    private IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    public void initialize()
    {
        currentProducerAccount = persistenceHandler.getProducerAccount(LoginController.getLoggedInID());
        txtOldName.setText(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getName());
        txtOldMail.setText(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getEmail());
        txtOldPhone.setText(String.valueOf(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getPhoneNumber()));
    }

    public void handleProducerCreateProduction(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "ProducerCreateProduction.fxml", "Opret produktion");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleMyProductions(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "ProducerMyProductions.fxml", "Mine Produktioner");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleProducerLogOut(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "FrontPage.fxml", "Login");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveNewName(ActionEvent event)
    {
        if (!txtOldName.getText().isEmpty() && !txtNewName.getText().isEmpty() && !txtRepeatNewName.getText().isEmpty())
        {
            if (txtNewName.getText().equals(txtRepeatNewName.getText()))
            {
                lblNameStatus.setStyle(null);
                int id = LoginController.getLoggedInID();
                String username = currentProducerAccount.getUsername();
                String password = currentProducerAccount.getPassword();
                String name = txtRepeatNewName.getText();
                String email = currentProducerAccount.getEmail();
                int phone = currentProducerAccount.getPhoneNumber();
                String company = currentProducerAccount.getCompany();
                Producer account = new Producer(id, username, password, false, name, email, phone, company);
                persistenceHandler.changeAccountName(account);
                txtRepeatNewName.clear();
                txtNewName.clear();
                txtOldName.setText(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getName());
                lblNameStatus.setText("Navn ændret");

            } else
            {
                lblNameStatus.setStyle("-fx-text-fill: red");
                lblNameStatus.setText("Det indtastede navn stemmer ikke");
            }
        }
    }

    public void saveNewPhone(ActionEvent event)
    {
        if (!txtOldPhone.getText().isEmpty() && !txtNewPhone.getText().isEmpty() && !txtRepeatNewPhone.getText().isEmpty())
        {
            if (txtNewPhone.getText().equals(txtRepeatNewPhone.getText()))
            {
                if (!persistenceHandler.checkPhone(Integer.parseInt(txtNewPhone.getText())))
                {
                    txtNewPhone.setStyle("-fx-border-color: red;");
                    lblPhoneStatus.setStyle("-fx-text-fill: red");
                    lblPhoneStatus.setText("Telefonnr. eksisterer allerede");
                } else
                {
                    txtNewPhone.setStyle(null);
                    txtRepeatNewPhone.setStyle(null);
                    lblPhoneStatus.setStyle(null);

                    int id = LoginController.getLoggedInID();
                    String username = currentProducerAccount.getUsername();
                    String password = currentProducerAccount.getPassword();
                    String name = txtRepeatNewName.getText();
                    String email = currentProducerAccount.getEmail();
                    int phone = Integer.parseInt(txtNewPhone.getText());
                    String company = currentProducerAccount.getCompany();
                    Producer account = new Producer(id, username, password, false, name, email, phone, company);
                    persistenceHandler.changeAccountPhone(account);

                    txtNewPhone.clear();
                    txtRepeatNewPhone.clear();
                    txtOldPhone.setText(String.valueOf(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getPhoneNumber()));
                    lblPhoneStatus.setText("Telefonnr. ændret");
                }
            } else
            {
                txtRepeatNewPhone.setStyle("-fx-border-color: red;");
                lblPhoneStatus.setStyle("-fx-text-fill: red");
                lblPhoneStatus.setText("Det genindtastede telefonnr. stemmer ikke");
            }
        }
    }

    public void saveNewMail(ActionEvent event)
    {
        System.out.println(persistenceHandler.checkAccountEmail(txtNewMail.getText()));
        if (!txtOldMail.getText().isEmpty() & !txtNewMail.getText().isEmpty() && !txtRepeatNewMail.getText().isEmpty())
        {
            if (txtNewMail.getText().equals(txtRepeatNewMail.getText()))
            {
                if (persistenceHandler.checkAccountEmail(txtNewMail.getText()))
                {
                    txtNewMail.setStyle("-fx-border-color: red;");
                    lblEmailStatus.setStyle("-fx-text-fill: red");
                    lblEmailStatus.setText("Email eksisterer allerede");
                } else
                {
                    txtNewMail.setStyle(null);
                    txtRepeatNewMail.setStyle(null);
                    lblEmailStatus.setStyle(null);

                    int id = LoginController.getLoggedInID();
                    String username = currentProducerAccount.getUsername();
                    String password = currentProducerAccount.getPassword();
                    String name = txtRepeatNewName.getText();
                    String email = txtNewMail.getText();
                    int phone = currentProducerAccount.getPhoneNumber();
                    String company = currentProducerAccount.getCompany();
                    Producer account = new Producer(id, username, password, false, name, email, phone, company);
                    persistenceHandler.changeAccountEmail(account);

                    txtNewMail.clear();
                    txtRepeatNewMail.clear();
                    txtOldMail.setText(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getEmail());
                    lblEmailStatus.setText("Email ændret");
                }
            } else
            {
                txtRepeatNewMail.setStyle("-fx-border-color: red;");
                lblEmailStatus.setStyle("-fx-text-fill: red");
                lblEmailStatus.setText("Den genindtastede email stemmer ikke");
            }
        }
    }

    public void saveNewPassword(ActionEvent event)
    {
        if (!txtOldPassword.getText().isEmpty() && !txtNewPassword.getText().isEmpty() && !txtRepeatNewPassword.getText().isEmpty())
        {
            if (txtNewPassword.getText().equals(txtRepeatNewPassword.getText()))
            {
                lblPasswordStatus.setStyle(null);

                int id = LoginController.getLoggedInID();
                String username = currentProducerAccount.getUsername();
                String password = txtNewPassword.getText();
                String name = txtRepeatNewName.getText();
                String email = currentProducerAccount.getEmail();
                int phone = currentProducerAccount.getPhoneNumber();
                String company = currentProducerAccount.getCompany();
                Producer account = new Producer(id, username, password, false, name, email, phone, company);

                persistenceHandler.changeAccountPassword(account);

                txtOldPassword.clear();
                txtNewPassword.clear();
                txtRepeatNewPassword.clear();
                lblPasswordStatus.setText("Password gemt");

            } else
            {
                lblPasswordStatus.setStyle("-fx-text-fill: red");
                lblPasswordStatus.setText("Det indtastede password stemmer ikke oversens");
            }
        }
    }
}
