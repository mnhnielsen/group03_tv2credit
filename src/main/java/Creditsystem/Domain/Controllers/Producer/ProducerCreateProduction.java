package Creditsystem.Domain.Controllers.Producer;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.*;
import Creditsystem.Domain.Controllers.Login.LoginController;
import Creditsystem.Domain.Helpers.CreditTable;
import Creditsystem.Domain.Helpers.StageChange;
import Creditsystem.Domain.Model.Credit;
import Creditsystem.Domain.Model.Participant;
import Creditsystem.Domain.Model.Production;
import Creditsystem.Domain.Model.Role;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ProducerCreateProduction
{
    public TableView tblCredit;
    public TextField txtTitle, txtYear, txtName, txtJob, participantEmail, participantPhone;
    public TextArea txtDescription;
    public Label lblTitle, lblReleaseYear, statusLabel, lblParticipantStatus;

    private TableColumn roleColumn;
    private StageChange stageChange = new StageChange();
    private IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    private Production production = null;
    private Role role = null;
    private Participant participant = null;
    private Credit credits = null;
    private boolean foundParticipant = false;
    private boolean foundRole = false;
    private boolean hasAddedProduction = false, hasCreatedColumns = false, hasSearchedPerson = false;

    public void initialize()
    {
        participantEmail.setEditable(false);
        participantPhone.setEditable(false);
    }

    public void searchForPerson(ActionEvent event)
    {
        participantEmail.setEditable(true);
        participantPhone.setEditable(true);
        hasSearchedPerson = true;
        participantPhone.clear();
        participantEmail.clear();
        participantPhone.setStyle(null);
        participantEmail.setStyle(null);
        for (Participant participant : persistenceHandler.getParticipants())
        {
            if (txtName.getText().toLowerCase().equals(participant.getName()))
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
            if (txtJob.getText().toLowerCase().equals(role.getName()))
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
            participantEmail.setText(persistenceHandler.getParticipant(txtName.getText().toLowerCase()).getEmail());
            participantPhone.setText(String.valueOf(persistenceHandler.getParticipant(txtName.getText().toLowerCase()).getPhoneNumber()));
        } else
        {
            participantEmail.setStyle("-fx-border-color: red;");
            participantPhone.setStyle("-fx-border-color: red;");
        }
    }

    //Creates participants, credits, columns and adds to a credit list
    public void createCredit(ActionEvent event)
    {
        lblParticipantStatus.setText("");
        participantPhone.setEditable(false);
        participantEmail.setEditable(false);
        hasSearchedPerson = false;
        participantEmail.setStyle(null);
        participantPhone.setStyle(null);

        if (!txtName.getText().isEmpty() && !txtJob.getText().isEmpty() && !participantPhone.getText().isEmpty() && !participantEmail.getText().isEmpty() && hasAddedProduction)
        {
            if (!foundParticipant)
            {
                //Create a participant
                participant = new Participant(txtName.getText().toLowerCase(), Integer.parseInt(participantPhone.getText()), participantEmail.getText().toLowerCase());
                persistenceHandler.createParticipant(participant);
            } else
            {
                //Set participant ID to existing participant
                persistenceHandler.getParticipantID(txtName.getText().toLowerCase());
            }


            if (!foundRole)
            {
                //Create a role
                role = new Role(txtJob.getText().toLowerCase());
                persistenceHandler.createRole(role);
            } else
            {   //Set role id to existing role
                persistenceHandler.getRoleID(txtJob.getText().toLowerCase());
            }

            //Create a Credit
            credits = new Credit(persistenceHandler.getProductionID(), persistenceHandler.getRoleID(), persistenceHandler.getParticipantID());
            persistenceHandler.createCredit(credits);
            lblParticipantStatus.setText(txtName.getText() + " blev krediteret i " + txtTitle.getText() + " som " + txtJob.getText());


            //Visual stuff
            Participant participants = new Participant(txtName.getText());
            CreditTable credit = new CreditTable(participants, txtJob.getText(), participants.getName());

            if (!hasCreatedColumns)
            {
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
                hasCreatedColumns = true;
            }
            tblCredit.getItems().add(credit);

            //Clear name and job
            txtJob.clear();
            txtName.clear();
            participantEmail.clear();
            participantPhone.clear();
        }
    }


    //Creates a new production and resets all information when published.
    public void publishProduction(ActionEvent event)
    {
        if (this.production != null)
        {
            statusLabel.setText("Din produktion med titlen: " + production.getTitle() + " er nu sendt til godkendelse. " + "Du modtager en mail på: " + persistenceHandler.getProducerAccount(LoginController.getLoggedInID()).getEmail() + " ved udgivelse");

            clearInfo();
        }
    }

    public void addProduction(ActionEvent event)
    {
        if (!txtTitle.getText().isEmpty() && !txtYear.getText().isEmpty() && !txtDescription.getText().isEmpty())
        {
            //Create production
            production = new Production(txtTitle.getText(), Integer.parseInt(txtYear.getText()), txtDescription.getText(), LoginController.getLoggedInID(), false);
            persistenceHandler.createProduction(production);
            hasAddedProduction = true;
            lblTitle.setText(txtTitle.getText());
            lblReleaseYear.setText(String.valueOf(txtYear.getText()));
        }
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
