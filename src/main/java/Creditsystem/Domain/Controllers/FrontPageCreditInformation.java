package Creditsystem.Domain.Controllers;

import Creditsystem.Domain.Helpers.CreditJoin;
import Creditsystem.Domain.Helpers.StageChange;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.text.WordUtils;


public class FrontPageCreditInformation
{
    public Label lblTitle;
    public Label lblReleaseYear;
    public TextArea txtDescription;
    public TableView tabelView;
    private TableColumn role = new TableColumn();

    StageChange stageChange = new StageChange();

    public void initialize()
    {
        lblTitle.setText(FrontPageController.getProduction().getTitle());
        lblReleaseYear.setText(String.valueOf(FrontPageController.getProduction().getReleaseYear()));
        txtDescription.setText(FrontPageController.getProduction().getDescription());

        role = new TableColumn("Rolle");
        role.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        role.setMinWidth(10);
        role.setPrefWidth(192);
        role.setMaxWidth(5000);


        TableColumn name = new TableColumn("Navn");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(10);
        name.setPrefWidth(192);
        name.setMaxWidth(5000);

        //Add to columns
        tabelView.getColumns().addAll(role, name);

        for (CreditJoin creditJoin : FrontPageController.getCredits())
        {
            String actorName = WordUtils.capitalize(creditJoin.getName());
            String roleName = WordUtils.capitalize(creditJoin.getRoleName());
            CreditJoin cj = new CreditJoin(actorName , roleName, creditJoin.getReleaseYear());
            tabelView.getItems().add(cj);
        }
    }

    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event, "FrontPage.fxml", "Krediterings forside");
    }
}
