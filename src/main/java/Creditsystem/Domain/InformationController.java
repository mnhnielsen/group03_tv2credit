package Creditsystem.Domain;

import javafx.event.ActionEvent;
import javafx.scene.control.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InformationController
{
    //TODO fix this!
    public TextArea creditInfo_txtArea;
    public Label lblTitle;
    public Label lblReleaseYear;
    public TextArea txtDescription;
    public TableView tblViewCredits;
    public ListView list;


    StageChange stageChange = new StageChange();

    public void initialize()
    {
        lblTitle.setText(FrontPageController.getProgram().get(0).toString().substring(7));
        lblReleaseYear.setText(FrontPageController.getProgram().get(1).toString().substring(14));
        txtDescription.setText(FrontPageController.getProgram().get(2).toString().substring(13));

        //tblViewCredits.getItems().add(FrontPageController.getProgram().get(4).toString().substring(7));
        for (int i = 4; i < FrontPageController.getProgram().size(); i++)
        {
            list.getItems().add(FrontPageController.getProgram().get(i).toString().replace("[","").replace("]","").replace(", ",""));
        }
    }

    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event, "FrontPage.fxml", "Krediterings forside");
    }
}
