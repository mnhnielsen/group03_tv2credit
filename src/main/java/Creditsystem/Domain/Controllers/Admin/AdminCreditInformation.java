package Creditsystem.Domain.Controllers.Admin;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.Helpers.CreditJoin;
import Creditsystem.Domain.Helpers.StageChange;
import Creditsystem.Domain.IPersistenceHandler;
import Creditsystem.Domain.Model.Participant;
import Creditsystem.Domain.Model.Role;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;

public class AdminCreditInformation
{
    public Label lblTitle;
    public Label lblReleaseYear;
    public TextArea txtDescription;
    public TextField phone, email;
    public TableView tabelView;
    private TableColumn role = new TableColumn("Rolle");
    TableColumn name = new TableColumn("Navn");
    private String newName, newRoleName;
    private StageChange stageChange = new StageChange();
    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    public void initialize()
    {
        phone.setDisable(true);
        email.setDisable(true);
        lblTitle.setText(AdminProductionList.getProduction().getTitle());
        lblReleaseYear.setText(String.valueOf(AdminProductionList.getProduction().getReleaseYear()));
        txtDescription.setText(AdminProductionList.getProduction().getDescription());
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
                        for (int i = 0; i < persistenceHandler.getParticipants().size(); i++)
                        {
                            if (!newName.toLowerCase().equals(persistenceHandler.getParticipants().get(i).getName()))
                            {
                                phone.setDisable(false);
                                email.setDisable(false);

                            } else
                            {
                                phone.setText(String.valueOf(persistenceHandler.getParticipant(newName.toLowerCase()).getPhoneNumber()));
                                email.setText(persistenceHandler.getParticipant(newName.toLowerCase()).getEmail().toLowerCase());
                                phone.setDisable(true);
                                email.setDisable(true);
                                phone.setEditable(false);
                                phone.setEditable(false);
                            }
                        }
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
                        ((CreditJoin) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setRoleName(t.getNewValue());
                        newRoleName = t.getNewValue();

                    }
                }
        );
        //Add to columns
        tabelView.getColumns().addAll(role, name);

        for (CreditJoin creditJoin : AdminProductionList.getCredits())
        {
            String actorName = WordUtils.capitalize(creditJoin.getName());
            String roleName = WordUtils.capitalize(creditJoin.getRoleName());
            CreditJoin cj = new CreditJoin(actorName , roleName, creditJoin.getReleaseYear());
            tabelView.getItems().add(cj);
        }
    }

    //Update button
    public void editColumns()
    {
        boolean roleExistance = false, nameExistance = false;
        if (newRoleName != null)
        {
            for (int i = 0; i < persistenceHandler.getRoles().size(); i++)
            {
                if (newRoleName.equals(persistenceHandler.getRoles().get(i).getName()))
                {
                    roleExistance = true;
                    break;
                } else
                {
                    roleExistance = false;
                }
            }
            if (roleExistance)
            {

                System.out.println("Changed role id for: " + newRoleName);
            } else
            {
                Role role = new Role(newRoleName);
                System.out.println("Create a new role for:" + role.getName());
            }
        }

        if (newName != null)
        {
            for (int i = 0; i < persistenceHandler.getParticipants().size(); i++)
            {
                if (newName.equals(persistenceHandler.getParticipants().get(i).getName()))
                {
                    nameExistance = true;
                    break;
                } else
                {
                    nameExistance = false;

                }
            }
            if (nameExistance)
            {
                System.out.println("Change id");
            } else
            {
                //Create a participant
                if (!phone.getText().isEmpty() && !email.getText().isEmpty())
                {
                    Participant participant = new Participant(newName, Integer.parseInt(phone.getText()), email.getText());
                    System.out.println("New Participant created: " + participant.getName() + " " + participant.getPhoneNumber() + " " + participant.getEmail());
                }
            }
        }
        phone.setDisable(true);
        email.setDisable(true);
        phone.clear();
        email.clear();
        phone.setEditable(true);
        phone.setEditable(true);
    }

    public void saveProduction(ActionEvent event)
    {
        try
        {
            new StageChange().openNewWindow(event, "Publications.fxml", "Systemadministrator forside");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event, "AdminPage.fxml", "Krediterings forside");
    }

    public void handleMyAdminCreateUser(ActionEvent event)
    {
    }

    public void handleDeleteAccount(ActionEvent event)
    {
    }

    public void handlePublications(ActionEvent event)
    {
    }

    public void handleAdminProfile(ActionEvent event)
    {
    }

    public void handleAdminLogOut(ActionEvent event)
    {
    }
}
