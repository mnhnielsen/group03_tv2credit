package Creditsystem.Domain.Controllers.Producer;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.Controllers.Login.LoginController;
import Creditsystem.Domain.Helpers.StageChange;
import Creditsystem.Domain.IPersistenceHandler;
import Creditsystem.Domain.Model.Production;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

import java.io.IOException;

public class ProducerCreditOverview
{
    public ListView myProgramList;
    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
    private StageChange stageChange = new StageChange();

    public void initialize()
    {
        for (Production pr : persistenceHandler.getMyProductions(LoginController.getLoggedInID()))
        {
            myProgramList.getItems().add(pr.getTitle());
        }

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
}
