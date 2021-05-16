package Creditsystem.Domain;

import Creditsystem.Data.FileHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProducerController
{
    //TODO create production, credit, cast. Store in arraylist. Save arraylist to local file.
    public Button btnBack, btnAddCredit, btnSendCredit, btnDeleteCredit;
    public TableView tblCredit;
    public TextField txtTitle, txtYear, txtName, txtJob;
    public TextArea txtDescription;

    Credit credit;
    Participant participant;
    List<Credit> creditList;
    Production production;
    static int idCounter = 1;
    TableColumn role;
    StageChange stageChange = new StageChange();
    LoginController controller = new LoginController();
    IFileHandler fileHandler = new FileHandler();

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
        //Check if info filed != null

        //Create credits, participants
        participant = new Participant(txtName.getText());
        credit = new Credit(participant, txtJob.getText(), participant.getName());
        creditList.add(credit);

        //Initiate Columns
        role = new TableColumn("Rolle");
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        role.setMinWidth(10);
        role.setPrefWidth(192);
        role.setMaxWidth(5000);

        TableColumn name = new TableColumn("Navn");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(10);
        name.setPrefWidth(192);
        name.setMaxWidth(5000);

        //Add to columns
        tblCredit.getColumns().addAll(role, name);
        tblCredit.getItems().add(credit);

        //Clear name and job
        txtJob.clear();
        txtName.clear();
    }

    //Creates a new production and resets all information when published.
    public void publishProduction(ActionEvent event)
    {
        if (tblCredit.getItems() != null)
        {
            production = new Production(idCounter, txtTitle.getText(),
                    txtDescription.getText(), Integer.parseInt(txtYear.getText()), creditList);

            idCounter++;

            tblCredit.getItems().clear();
            txtTitle.clear();
            txtDescription.clear();
            txtYear.clear();

            fileHandler.writeToFile(production, "Files/Productions/" + production.getTitle());
            creditList.removeAll(creditList);
        }
    }

    //Deletes a selected credit from the list. Does not get published if deleted from list.
    public void deleteCredit(ActionEvent event)
    {
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
    }

    public Production createProduction(int id, String title, String description, int releaseYear, List<Credit> credits)
    {
        return new Production(id, title, description, releaseYear, credits);
    }
}
