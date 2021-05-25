package Creditsystem.Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductionTest {

    @Test
    public void getTitle() {
        Production c = new Production(1, "Druk");
        assertEquals("Dru", c.getTitle());
    }
}