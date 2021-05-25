package Creditsystem.Data;

import Creditsystem.Domain.Production;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.PreparedStatement;

import static org.junit.Assert.assertEquals;

public class PersistenceHandlerTest {
    private PersistenceHandler persistenceHandler = new PersistenceHandler() ;

    public PersistenceHandlerTest(){}
    @Test
    public void createProduction() {
        Production production = new Production(1,"Druk",2021,"test",2,false);
        Assertions.assertEquals(true,persistenceHandler.createProduction(production));
    }

    @Test
    public void logIn()
    {
        Assertions.assertEquals(true,persistenceHandler.logIn("Mathias","1234"));
    }

}