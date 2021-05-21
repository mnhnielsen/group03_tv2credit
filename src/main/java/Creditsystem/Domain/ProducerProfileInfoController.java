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
    private ProducerAccount currentProducerAccount;
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
                int id ;
                String username;
                String password;
                String name;
                String email;
                int phone;
                String company;
                ProducerAccount account = new ProducerAccount();
                persistenceHandler.changeAccountName()
            }
        }
    }

    public void saveNewPhone(ActionEvent event)
    {
    }

    public void saveNewMail(ActionEvent event)
    {
    }

    public void saveNewPassword(ActionEvent event)
    {
    }
}
