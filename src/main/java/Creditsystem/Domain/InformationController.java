package Creditsystem.Domain;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class InformationController
{
    //TODO fix this!
    public TextArea creditInfo_txtArea;
    public Label lblTitle;
    public Label lblReleaseYear;
    public TextArea txtDescription;
    public TableView tblViewCredits;
    private TableColumn titleColumn = new TableColumn();
    private TableColumn titleColumn2 = new TableColumn();
    StageChange stageChange = new StageChange();

    public void initialize()
    {
        lblTitle.setText(FrontPageController.getProgram().get(0).toString().substring(7));
        lblReleaseYear.setText(FrontPageController.getProgram().get(1).toString().substring(14));
        txtDescription.setText(FrontPageController.getProgram().get(2).toString().substring(13));

        // Column
        titleColumn.setText("Rolle");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("rolle"));
        titleColumn.setMinWidth(10);
        titleColumn.setPrefWidth(192);
        titleColumn.setMaxWidth(5000);

        titleColumn2.setText("Navn");
        titleColumn2.setMinWidth(10);
        titleColumn2.setPrefWidth(192);
        titleColumn2.setMaxWidth(5000);

        tblViewCredits.getColumns().addAll(titleColumn,titleColumn2);

    }


    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event,"FrontPage.fxml","Krediterings forside");
    }
}
