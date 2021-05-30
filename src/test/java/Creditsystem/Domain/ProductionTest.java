package Creditsystem.Domain;

import Creditsystem.Domain.Model.Production;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductionTest {

    @Test
    public void getTitle() {
        Production c = new Production(1, "Druk");
        assertEquals("Dru", c.getTitle());
    }
}