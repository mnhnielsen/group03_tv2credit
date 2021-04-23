package Creditsystem.Domain;

import java.util.ArrayList;

public interface IFileHandler
{
    public ArrayList<String> readFile(String fileName);
    public  boolean writeToFile(Object obj, String fileName);
}
