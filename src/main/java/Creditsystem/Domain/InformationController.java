package Creditsystem.Domain;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class InformationController
{
    //TODO fix this!
    public TextArea creditInfo_txtArea;
    public Label title;
    StageChange stageChange = new StageChange();

    public void initialize()
    {
        title.setText(FrontPageController.getProgram().get(0).toString().substring(6));
    }

    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event,"FrontPage.fxml","Krediterings forside");
    }
}
