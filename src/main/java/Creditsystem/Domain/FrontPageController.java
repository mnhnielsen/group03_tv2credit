package Creditsystem.Domain;

import Creditsystem.Data.FileHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FrontPageController
{
    public TextField txtSearch;
    public TableView tblCredits;

    StageChange stageChange = new StageChange();

    ArrayList list = new ArrayList();

    private int noFiles = 0;

    public void initialize()
    {
        ArrayList<Production> productionArrayList = new ArrayList<>();
        String title;
        Production production = null;
        File file = new File("Files/Productions/");
        list = findFiles(file);

        for (int i = 0; i < noFiles; i++)
        {
            title = list.get(i).toString();
            production = new Production(title);
            productionArrayList.add(production);
        }

        TableColumn name = new TableColumn("Title");
        name.setCellValueFactory(new PropertyValueFactory<>("title"));
        name.setMinWidth(10);
        name.setPrefWidth(192);
        name.setMaxWidth(5000);

        tblCredits.getColumns().addAll(name);
        for (Production pr : productionArrayList)
        {
            tblCredits.getItems().add(pr);

        }
    }

    //This could be moved to Data.FileHandler
    private ArrayList findFiles(File file)
    {
        ArrayList<String> fileList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File f : files)
        {                       // Loop through files found
            if (f.isDirectory())
            {                   // Check if is directory.
                findFiles(f.getAbsoluteFile());     // Get directory if any

            } else
            {
                fileList.add(f.getName());                      //Print files path
                noFiles++;                                      //Count # files
            }
        }
        return fileList;
    }

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
