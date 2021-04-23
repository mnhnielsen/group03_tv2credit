package Creditsystem.Domain;

import Creditsystem.Data.FileHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FrontPageController
{
    //Javafx attributes
    public TextField txtSearch;
    public TableView tblCredits;
    public Button btnOpenCredit;


    private StageChange stageChange = new StageChange();
    private TableColumn titleColumn = new TableColumn();
    private int noFiles = 0;
    ArrayList<Production> productionArrayList = new ArrayList<>();

    IFileHandler fileHandler = new FileHandler();

    public void initialize()
    {
        ArrayList list = new ArrayList();

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

        //Column stuff
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setMinWidth(10);
        titleColumn.setPrefWidth(192);
        titleColumn.setMaxWidth(5000);

        tblCredits.getColumns().addAll(titleColumn);

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

    public void openCredit(ActionEvent event)
    {
        try
        {
            ArrayList<String> credits = new ArrayList<>();
            Object obj = tblCredits.getSelectionModel().getSelectedItem();
            String data = (String) titleColumn.getCellObservableValue(obj).getValue();

            for (int i = 0; i < noFiles; i++)
            {
                if (data == productionArrayList.get(i).getTitle())
                {
                    credits = fileHandler.readFile("Files/Productions/" + productionArrayList.get(i).getTitle());
                }
            }
            System.out.println(credits);
        } catch (Exception e)
        {
            System.out.println("Select a program first");
        }
    }
}
