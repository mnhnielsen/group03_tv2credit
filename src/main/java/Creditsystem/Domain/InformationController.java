package Creditsystem.Domain;

import javafx.event.ActionEvent;
import javafx.scene.control.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InformationController
{
    //TODO fix this!
    public Label lblTitle;
    public Label lblReleaseYear;
    public TextArea txtDescription;
    public ListView list;

    StageChange stageChange = new StageChange();

    public void initialize()
    {
        lblTitle.setText(FrontPageController.getProduction().getTitle());
        lblReleaseYear.setText(String.valueOf(FrontPageController.getProduction().getReleaseYear()));
        txtDescription.setText(FrontPageController.getProduction().getDescription());

    }

    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event, "FrontPage.fxml", "Krediterings forside");
    }
}
