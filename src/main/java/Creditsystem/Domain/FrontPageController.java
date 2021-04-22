package Creditsystem.Domain;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FrontPageController
{
    public Button btnLogin;
    public TextField txtSearch;
    public TableView tblCredits;

    StageChange stageChange = new StageChange();
    public void handleLogin(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "LoginPage.fxml", "Login");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
