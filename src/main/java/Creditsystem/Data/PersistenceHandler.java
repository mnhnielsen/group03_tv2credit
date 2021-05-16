package Creditsystem.Data;

import Creditsystem.Domain.Account;
import Creditsystem.Domain.IPersistenceHandler;
import Creditsystem.Domain.ProducerAccount;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersistenceHandler implements IPersistenceHandler
{
    private static PersistenceHandler instance;
    private String url;
    private int port;
    private String databaseName;
    private String username;
    private String password;
    private Connection connection = null;

    private void initializeConfiguration()
    {
        try
        {
            InputStream inputStream = new FileInputStream(getClass().getResource("config.properties").toURI().getPath());
            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty("db.url");
            port = Integer.valueOf(properties.getProperty("db.port"));
            databaseName = properties.getProperty("db.databaseName");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private PersistenceHandler()
    {
        initializeConfiguration();
        initializePostgresqlDatabase();
    }

    public static PersistenceHandler getInstance()
    {
        if (instance == null)
        {
            instance = new PersistenceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase()
    {
        try
        {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            System.out.println("Connected");
        } catch (SQLException | IllegalArgumentException ex)
        {
            ex.printStackTrace(System.err);
        } finally
        {
            if (connection == null) System.exit(-1);
        }
    }

    @Override
    public boolean logIn(String username, String password)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ? and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
            {
                return false;
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean isAdmin(String username)
    {

        boolean isAdmin = false;
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            statement.setString(1, username);

            ResultSet sqlReturnValues = statement.executeQuery();
            List<Account> returnValue = new ArrayList<>();
            Account account = null;
            while (sqlReturnValues.next())
            {
                account = new Account(sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getBoolean(4));
                returnValue.add(account);
            }
            isAdmin = account.getAdminStatus();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return isAdmin;
    }

    @Override
    public boolean checkAuthorization(String username)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
            {
                return false;
            }
            if (resultSet.getString(1).equals(username))
            {
                System.out.println("Same username");
                return false;
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean createAdminAccount(Account account)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO accounts(username,password,isadmin)VALUES (?,?,?)");
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setBoolean(3, account.getAdminStatus());
            statement.execute();
        } catch (SQLException throwables)
        {
            System.out.println("Could not create new login");
            return false;
        }
        return true;
    }

    @Override
    public boolean createProducerAccount(ProducerAccount producerAccount)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO produceraccount(username,password,isadmin, name, email, phoneNumber, company)VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, producerAccount.getUsername());
            statement.setString(2, producerAccount.getPassword());
            statement.setBoolean(3, producerAccount.getAdminStatus());
            statement.setString(4,producerAccount.getName());
            statement.setString(5,producerAccount.getEmail());
            statement.setInt(6,producerAccount.getPhoneNumber());
            statement.setString(7,producerAccount.getCompany());
            statement.execute();
        } catch (SQLException throwables)
        {
            System.out.println("Could not create new login");
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}
