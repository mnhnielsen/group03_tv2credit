package Creditsystem.Domain;

import Creditsystem.Domain.Helpers.StageChange;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.*;

import java.util.Scanner;


public class LoginController
{

    //Javafx attributes
    public Hyperlink registerLogin;
    public Hyperlink forgotLogin;
    public Button btnLogin;
    public ImageView goBack;
    public TextField txtUsername;
    public PasswordField passwordField;

    StageChange stageChange = new StageChange();


    public void handleForgotLogin(ActionEvent event)
    {
        System.out.println("handleForgotLogin");
    }

    public void handleLogin(ActionEvent event)
    {
        boolean pUsername = false;
        boolean aUsername = false;
        boolean password = false;
        if (!txtUsername.getText().trim().isEmpty() && !passwordField.getText().trim().isEmpty())
        {

            File file = new File("Files/Accounts/accounts.json");
            try
            {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext())
                {
                    String line = scanner.nextLine();
                    if (line.indexOf(txtUsername.getText()) != -1)
                    {
                        if(txtUsername.getText().startsWith("admin"))
                            aUsername = true;
                        else if (txtUsername.getText().startsWith("producer"))
                            pUsername = true;
                    }
                    if (line.indexOf(passwordField.getText()) != -1)
                    {
                        password = true;
                    }

                }
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            if (password)
            {
                if (pUsername)
                {
                    try
                    {
                        stageChange.openNewWindow(event, "producerPage.fxml", "Producer Forside");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if(aUsername){
                    try
                    {
                        stageChange.openNewWindow(event, "AdminPage.fxml", "Systemadminstrator Forside");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public void handleRegisterLogin(ActionEvent event)
    {
        System.out.println("handleRegisterLogin");
    }


    public void handleBackButton(ActionEvent event)
    {
        try
        {
            stageChange.openNewWindow(event, "FrontPage.fxml", "Krediteringsforside");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
