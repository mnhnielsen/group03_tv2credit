package Creditsystem.Domain;

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
    static int counter = 1;
    TableColumn role;
    StageChange stageChange = new StageChange();

    public void initialize()
    {
        creditList = new ArrayList<>();
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

    public void handleAddCreditButton(ActionEvent event)
    {
        //Create credits, participants
        participant = new Participant(txtName.getText());
        credit = new Credit(participant, txtJob.getText(), participant.getName());
        creditList.add(credit);
        //Create columns
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

    public void handlePublish(ActionEvent event)
    {
        if (tblCredit.getItems() != null)
        {
            production = new Production(counter, txtTitle.getText(), txtDescription.getText(), Integer.parseInt(txtYear.getText()), creditList);
            System.out.println(production.toString());
            counter++;
            tblCredit.getItems().clear();
            txtTitle.clear();
            txtDescription.clear();
            txtYear.clear();
            creditList.removeAll(creditList);
        }
    }

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
}
