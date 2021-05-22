package Creditsystem.Domain;

import Creditsystem.Data.PersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProducerProfileInfoController
{

    public TextField txtOldName, txtNewName, txtRepeatNewName,
            txtOldPhone, txtNewPhone, txtRepeatNewPhone, txtOldMail,
            txtNewMail, txtRepeatNewMail, txtOldPassword, txtNewPassword,
            txtRepeatNewPassword;
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
            } else
            {
                System.out.println("Not the same");
            }
        } else
        {
            System.out.println("Fill out more field");
        }

    }

    public void saveNewPhone(ActionEvent event)
    {

        if (!txtOldPhone.getText().isEmpty() && !txtNewPhone.getText().isEmpty() && !txtRepeatNewPhone.getText().isEmpty())
        {
            if (txtNewPhone.getText().equals(txtRepeatNewPhone.getText()))
            {
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
            }
            else
            {
                System.out.println("Not the same");
            }
        }
        else
        {
            System.out.println("Fill out more field");
        }

    }

    public void saveNewMail(ActionEvent event)
    {
        if (!txtOldMail.getText().isEmpty() & !txtNewMail.getText().isEmpty() && !txtRepeatNewMail.getText().isEmpty())
        {
            if (txtNewMail.getText().equals(txtRepeatNewMail.getText()))
            {
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
            }
            else
            {
                System.out.println("Not the same");
            }
        }
        else
        {
            System.out.println("Fill out more field");
        }
    }

    public void saveNewPassword(ActionEvent event)
    {
        if (!txtOldPassword.getText().isEmpty() && !txtNewPassword.getText().isEmpty() && !txtRepeatNewPassword.getText().isEmpty())
        {
            if (txtNewPassword.getText().equals(txtRepeatNewPassword.getText()))
            {
                int id = LoginController.getLoggedInID();
                String username = currentProducerAccount.getUsername();
                String password = txtNewPassword.getText();
                String name = txtRepeatNewName.getText();
                String email = currentProducerAccount.getEmail();
                int phone = currentProducerAccount.getPhoneNumber();
                String company = currentProducerAccount.getCompany();
                Producer account = new Producer(id, username, password, false, name, email, phone, company);
                persistenceHandler.changeAccountPassword(account);
                txtNewPassword.clear();
                txtRepeatNewPassword.clear();
                txtOldPassword.setText(String.valueOf(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getPassword()));
            }
            else
            {
                System.out.println("Error with saveNewPassword()");
            }
        }
        else
        {
            System.out.println("Fill out more field");
        }

    }
}
