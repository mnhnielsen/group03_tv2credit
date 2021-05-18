package Creditsystem.Domain;

import Creditsystem.Data.FileHandler;
import Creditsystem.Data.PersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.mail.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProducerController
{
    public TableView tblCredit;
    public TextField txtTitle, txtYear, txtName, txtJob, participantName, participantEmail, participantPhone;
    public TextArea txtDescription;
    public Label lblTitle, lblReleaseYear;

    List<Credit> creditList;
    TableColumn roleColumn;
    StageChange stageChange = new StageChange();
    LoginController controller = new LoginController();

    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    Production production = null;
    Role role = null;
    Participant participant = null;
    Credits credits = null;


    public void initialize()
    {
        creditList = new ArrayList<>();
        System.out.println(controller.getLoggedInID());
    }

    public void handleBackButton(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "FrontPage.fxml", "Krediterings Forside");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //Creates participants, credits, columns and adds to a credit list
    public void createCredit(ActionEvent event)
    {
        //Create a participant
        participant = new Participant(txtName.getText(), Integer.parseInt(participantPhone.getText()), participantEmail.getText());
        persistenceHandler.createParticipant(participant);

        //Create a role
        role = new Role(txtJob.getText());
        persistenceHandler.createRole(role);
        //Visual stuff
        Participant participants = new Participant(txtName.getText());
        Credit credit = new Credit(participants, txtJob.getText(), participants.getName());

        credits = new Credits(persistenceHandler.getProductionID(), persistenceHandler.getRoleID(), persistenceHandler.getParticipantID());
        persistenceHandler.createCredit(credits);

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

    }

    public void searchForPerson(ActionEvent event)
    {
        participantPhone.clear();
        participantEmail.clear();
        participantPhone.setStyle(null);
        participantEmail.setStyle(null);
        boolean found = false;
        for (Participant participant : persistenceHandler.getParticipants())
        {
            if (txtName.getText().equals(participant.getName())){
                found = true;
                break;
            }
            else{
                found = false;
            }
        }

        if(found){
            participantEmail.setText(persistenceHandler.getParticipant(txtName.getText()).getEmail());
            participantPhone.setText(String.valueOf(persistenceHandler.getParticipant(txtName.getText()).getPhoneNumber()));
        }
        else{
            participantEmail.setStyle("-fx-border-color: red;");
            participantPhone.setStyle("-fx-border-color: red;");
        }
       /* if (persistenceHandler.findParticipant(txtName.getText()))
        {
            System.out.println("Found");
            participantEmail.setText(persistenceHandler.getParticipant(txtName.getText()).getEmail());
            participantPhone.setText(String.valueOf(persistenceHandler.getParticipant(txtName.getText()).getPhoneNumber()));
        } else
        {
            System.out.println("Not found");


        }

        */
    }

    public void addProduction(ActionEvent event)
    {
        //Create production
        production = new Production(txtTitle.getText(), Integer.parseInt(txtYear.getText()), txtDescription.getText());
        persistenceHandler.createProduction(production);

        lblTitle.setText(txtTitle.getText());
        lblReleaseYear.setText(String.valueOf(txtYear.getText()));
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
}
