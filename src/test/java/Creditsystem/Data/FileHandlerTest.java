package Creditsystem.Data;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest
{
    FileHandler fileHandler = new FileHandler();

    @org.junit.jupiter.api.Test
    void readFile()
    {
        // virker ikke
        ArrayList<String> list = new ArrayList();
        assertEquals(list,fileHandler.readFile("Batman"));
    }

    @org.junit.jupiter.api.Test
    void writeToFile()
    {
        Assertions.assertEquals(true,fileHandler.writeToFile(new Object(),"Batman"));
    }
}