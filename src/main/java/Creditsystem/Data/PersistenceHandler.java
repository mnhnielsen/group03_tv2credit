package Creditsystem.Data;

import Creditsystem.Domain.IPersistenceHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersistenceHandler implements IPersistenceHandler
{
    private static PersistenceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "CreditDB";
    private String username = "postgres";
    private String password = "SyddanskTwb65mxa";
    private Connection connection = null;

    private PersistenceHandler()
    {
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
