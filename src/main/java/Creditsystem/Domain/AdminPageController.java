package Creditsystem.Domain;

import Creditsystem.Domain.Helpers.StageChange;
import javafx.event.ActionEvent;

public class AdminPageController
{
    StageChange stageChange = new StageChange();

    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event,"FrontPage.fxml","Krediterings forside");
    }
}
