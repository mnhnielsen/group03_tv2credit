package Creditsystem.Presentation;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.IPersistenceHandler;

public class DBTest
{
    public static void main(String[] args)
    {
        IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
    }
}
