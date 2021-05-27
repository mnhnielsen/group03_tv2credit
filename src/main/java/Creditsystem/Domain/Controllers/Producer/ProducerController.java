package Creditsystem.Domain.Controllers.Producer;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.*;
import Creditsystem.Domain.Controllers.LoginController;
import Creditsystem.Domain.Helpers.StageChange;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class ProducerController
{
    public Label welcomeLabel;
    private StageChange stageChange = new StageChange();
    private IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    public void initialize()
    {
        welcomeLabel.setText(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getName());
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

    public void handleMyProducerProfile(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "ProducerProfile.fxml", "Min profil");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
