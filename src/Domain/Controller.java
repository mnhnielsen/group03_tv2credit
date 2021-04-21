package Domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;


public class Controller
{
    //Javafx attributes
    public Hyperlink registerLogin;
    public Hyperlink forgotLogin;
    public Button btnLogin;
    public TextField txtUsername;
    public PasswordField passwordField;

    public void handleForgotLogin(ActionEvent event)
    {
        System.out.println("handleForgotLogin");
    }

    public void handleLogin(ActionEvent event)
    {
        System.out.println("handleLogin");
        loginFile("accounts.json");
    }

    public void handleRegisterLogin(ActionEvent event)
    {
        System.out.println("handleRegisterLogin");
    }

    public void loginFile(String filename)
    {
        File file = new File("C:\\Users\\Mathias\\Documents\\Projekt\\CreditSystem\\group03_tv2credit\\jsonFiles\\accounts.json");
        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
