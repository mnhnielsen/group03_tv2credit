package Creditsystem.Domain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class AdminCreditInformation
{
    public Label lblTitle;
    public Label lblReleaseYear;
    public TextArea txtDescription;
    public TableView tabelView;
    private TableColumn role = new TableColumn("Rolle");
    ;
    TableColumn name = new TableColumn("Navn");

    private String newRoleName, oldRoleName;
    private String newName, oldName;

    StageChange stageChange = new StageChange();

    public void initialize()
    {
        lblTitle.setText(AdminPageController.getProduction().getTitle());
        lblReleaseYear.setText(String.valueOf(AdminPageController.getProduction().getReleaseYear()));
        txtDescription.setText(AdminPageController.getProduction().getDescription());
        tabelView.setEditable(true);

        role.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        role.setMinWidth(10);
        role.setPrefWidth(192);
        role.setMaxWidth(5000);
        role.setEditable(true);


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(10);
        name.setPrefWidth(192);
        name.setMaxWidth(5000);
        name.setEditable(true);
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CreditJoin, String>>()
                {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CreditJoin, String> t)
                    {
                        ((CreditJoin) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                        newName = t.getNewValue();
                    }
                }
        );
        role.setCellFactory(TextFieldTableCell.forTableColumn());
        role.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CreditJoin, String>>()
                {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CreditJoin, String> t)
                    {
                        oldRoleName = t.getOldValue();
                        ((CreditJoin) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setRoleName(t.getNewValue());
                        newRoleName = t.getNewValue();

                    }
                }
        );
        //Add to columns
        tabelView.getColumns().addAll(role, name);

        for (CreditJoin creditJoin : AdminPageController.getCredits())
        {
            tabelView.getItems().add(creditJoin);
        }
    }

    //Update button
    public void editColumns()
    {
        System.out.println(oldRoleName);
        System.out.println(newRoleName);
        System.out.println(newName);
    }


    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event, "AdminPage.fxml", "Krediterings forside");

    }
}
