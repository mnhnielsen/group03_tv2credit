package Creditsystem.Data;

import Creditsystem.Domain.IPersistenceHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
