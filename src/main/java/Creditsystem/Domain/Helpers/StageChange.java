package Creditsystem.Domain.Helpers;

import Creditsystem.Presentation.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageChange
{
    public void openNewWindow(ActionEvent event, String path, String title) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource(path));

        Node node = (Node) event.getSource();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = new Stage();
        Stage currentStage = (Stage) node.getScene().getWindow();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        currentStage.close();
    }
    public void handleBackButton(ActionEvent event, String path, String title)
    {
        try
        {
            openNewWindow(event, path, title);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
