package Creditsystem.Domain;

import Creditsystem.Data.PersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerController
{
    public TableView tblCredit;
    public TextField txtTitle, txtYear, txtName, txtJob, participantEmail, participantPhone;
    public TextField txtDescription;
    public Label lblTitle, lblReleaseYear, statusLabel, welcomeLabel;
    public ListView myProgramList;

    List<Credit> creditList;
    TableColumn roleColumn;
    StageChange stageChange = new StageChange();

    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    Production production = null;
    Role role = null;
    Participant participant = null;
    Credits credits = null;
    boolean foundParticipant = false;
    boolean foundRole = false;


    public void initialize()
    {
        System.out.println(LoginController.getLoggedInID());
        welcomeLabel.setText(persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getName());
        /*creditList = new ArrayList<>();
        for (Production pr : persistenceHandler.getMyProductions(LoginController.getLoggedInID()))
        {
            myProgramList.getItems().add(pr.getTitle());
        }*/
    }

    public void searchForPerson(ActionEvent event)
    {
        participantPhone.clear();
        participantEmail.clear();
        participantPhone.setStyle(null);
        participantEmail.setStyle(null);
        for (Participant participant : persistenceHandler.getParticipants())
        {
            if (txtName.getText().equals(participant.getName()))
            {
                foundParticipant = true;
                break;
            } else
            {
                foundParticipant = false;
            }
        }

        for (Role role : persistenceHandler.getRoles())
        {
            if (txtJob.getText().equals(role.getName()))
            {
                foundRole = true;
                break;
            } else
            {
                foundRole = false;
            }
        }

        if (foundParticipant)
        {
            participantEmail.setText(persistenceHandler.getParticipant(txtName.getText()).getEmail());
            participantPhone.setText(String.valueOf(persistenceHandler.getParticipant(txtName.getText()).getPhoneNumber()));
        } else
        {
            participantEmail.setStyle("-fx-border-color: red;");
            participantPhone.setStyle("-fx-border-color: red;");
        }
    }

    //Creates participants, credits, columns and adds to a credit list
    public void createCredit(ActionEvent event)
    {
        if (!foundParticipant)
        {
            //Create a participant
            participant = new Participant(txtName.getText(), Integer.parseInt(participantPhone.getText()), participantEmail.getText());
            persistenceHandler.createParticipant(participant);
        } else
        {
            persistenceHandler.getParticipantID(txtName.getText());
        }
        if (!foundRole)
        {
            //Create a role
            role = new Role(txtJob.getText());
            persistenceHandler.createRole(role);
        } else
        {
            persistenceHandler.getRoleID(txtJob.getText());
        }

        //Create Credit
        credits = new Credits(persistenceHandler.getProductionID(), persistenceHandler.getRoleID(), persistenceHandler.getParticipantID());
        persistenceHandler.createCredit(credits);

        //Visual stuff
        Participant participants = new Participant(txtName.getText());
        Credit credit = new Credit(participants, txtJob.getText(), participants.getName());


        roleColumn = new TableColumn("Rolle");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setMinWidth(10);
        roleColumn.setPrefWidth(192);
        roleColumn.setMaxWidth(5000);

        TableColumn name = new TableColumn("Navn");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(10);
        name.setPrefWidth(192);
        name.setMaxWidth(5000);
        //Add to columns
        tblCredit.getColumns().addAll(roleColumn, name);
        tblCredit.getItems().add(credit);

        //Clear name and job
        txtJob.clear();
        txtName.clear();
        participantEmail.clear();
        participantPhone.clear();
    }


    //Creates a new production and resets all information when published.
    public void publishProduction(ActionEvent event)
    {
        statusLabel.setText("Din produktion med titlen" + production.getTitle() + " er nu sendt til godkendelse. " + "\n" + "Du modtager en mail på: " + persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getEmail() + " ved udgivelse");

        clearInfo();
    }


    public void addProduction(ActionEvent event)
    {
        //Create production
        production = new Production(txtTitle.getText(), Integer.parseInt(txtYear.getText()), txtDescription.getText(), LoginController.getLoggedInID(), false);
        persistenceHandler.createProduction(production);

        lblTitle.setText(txtTitle.getText());
        lblReleaseYear.setText(String.valueOf(txtYear.getText()));
    }

    public void clearInfo()
    {
        lblTitle.setText("");
        lblReleaseYear.setText("");
        tblCredit.getItems().clear();
        txtTitle.clear();
        txtDescription.clear();
        txtYear.clear();
    }

    //Deletes a selected credit from the list. Does not get published if deleted from list.
    public void deleteCredit(ActionEvent event)
    {
        /*
        Object obj = tblCredit.getSelectionModel().getSelectedItem();
        tblCredit.getItems().remove(obj);
        String data = (String) role.getCellObservableValue(obj).getValue();
        for (int i = 0; i < creditList.size(); i++)
        {
            if (data == creditList.get(i).getRole())
            {
                creditList.remove(i);
            }
        }

         */
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
