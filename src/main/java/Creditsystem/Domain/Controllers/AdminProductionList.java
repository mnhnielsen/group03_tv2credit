package Creditsystem.Domain.Controllers;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.CreditJoin;
import Creditsystem.Domain.Helpers.Mail;
import Creditsystem.Domain.Helpers.StageChange;
import Creditsystem.Domain.IPersistenceHandler;
import Creditsystem.Domain.Production;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminProductionList
{

    public ListView unreleasedList;
    public Label lblStatus;

    private IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
    private StageChange stageChange = new StageChange();
    private static Production production = null;
    private static ArrayList<CreditJoin> credits = new ArrayList<>();

    public void initialize()
    {
        for (Production production : persistenceHandler.getUnreleasedProductions())
        {
            unreleasedList.getItems().add(production.getTitle());
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

    public void removeFromList()
    {
        List<Integer> selectedItemsCopy = new ArrayList<>(unreleasedList.getSelectionModel().getSelectedItems());
        unreleasedList.getItems().removeAll(selectedItemsCopy);
    }

    public void getHighligtedProduction(ActionEvent event)
    {
        if (unreleasedList.getSelectionModel().getSelectedItem() != null)
        {
            String data = (String) unreleasedList.getSelectionModel().getSelectedItem();
            for (int i = 0; i < persistenceHandler.getUnreleasedProductions().size(); i++)
            {
                if (data.equals(persistenceHandler.getUnreleasedProductions().get(i).getTitle()))
                {
                    production = persistenceHandler.getProductionTitle(data);
                }
            }
        }
    }

    public void releaseProduction(ActionEvent event)
    {
        if (getProduction() != null)
        {
            getHighligtedProduction(event);
            removeFromList();
            persistenceHandler.releaseProduction(getProduction());
            Mail mail = new Mail();
            mail.sendEmail(persistenceHandler.getProducerAccount(getProduction().getCreatedby()).getEmail(), getProduction().getTitle() + " er nu udgivet", "" +
                    "Din produktion for " + getProduction().getTitle() + " er nu udgivet. Se den inde på Krediteringssystemet.");
            persistenceHandler.getProducerAccount(2);
            lblStatus.setText(getProduction().getTitle() + " er udgivet." + "\n" + "Email er sendt til " + persistenceHandler.getProducerAccount(getProduction().getCreatedby()).getEmail());

        }
    }

    public static Production getProduction()
    {
        return production;
    }

    public static ArrayList<CreditJoin> getCredits()
    {
        return credits;
    }


    public void viewProduction(ActionEvent event)
    {
        if (unreleasedList.getSelectionModel().getSelectedItem() != null)
        {
            String data = (String) unreleasedList.getSelectionModel().getSelectedItem();
            for (int i = 0; i < persistenceHandler.getUnreleasedProductions().size(); i++)
            {
                if (data.equals(persistenceHandler.getUnreleasedProductions().get(i).getTitle()))
                {
                    production = persistenceHandler.getProductionTitle(data);
                    credits = persistenceHandler.getCredits(data);
                    try
                    {
                        stageChange.openNewWindow(event, "Programvalidation.fxml", "Informations side");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
