package Creditsystem.Domain;

import Creditsystem.Data.PersistenceHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminPageController
{
    public TextField txtCreateName, txtName, txtEmail, txtPhone, txtCompany;
    public PasswordField pwrdPassword;
    public PasswordField pswrdReEnterPassword;
    public CheckBox adminCheck;

    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    StageChange stageChange = new StageChange();

    public void handleBackButton(ActionEvent event)
    {
        stageChange.handleBackButton(event, "FrontPage.fxml", "Krediterings forside");
    }

    public void createNewUser(ActionEvent event)
    {
        String username = txtCreateName.getText();
        String password = pwrdPassword.getText();
        String reEnter = pswrdReEnterPassword.getText();
        if (username != "" && password != "" && reEnter != "")
        {
            if (password.equals(reEnter))
            {
                if (!persistenceHandler.checkUsername(username))
                {
                    if (adminCheck.isSelected())
                    {
                        Account account = new Account(username, password, adminCheck.isSelected());
                        persistenceHandler.createAdminAccount(account);

                        txtCreateName.clear();
                        pwrdPassword.clear();
                        pswrdReEnterPassword.clear();
                        adminCheck.setSelected(false);
                    }
                    else {
                        ProducerAccount producerAccount = new ProducerAccount(username,password,false,txtName.getText(),txtEmail.getText(),Integer.valueOf(txtPhone.getText()),txtCompany.getText());
                        persistenceHandler.createProducerAccount(producerAccount);
                        txtCreateName.clear();
                        pwrdPassword.clear();
                        pswrdReEnterPassword.clear();
                        txtName.clear();
                        txtEmail.clear();
                        txtPhone.clear();
                        txtCompany.clear();
                    }
                } else
                {
                    System.out.println("username already exists!");
                }

            } else
            {
                System.out.println("Passwords are not the same");
            }
        } else
        {
            System.out.println("You must fill out all the fields");
        }
    }
}
