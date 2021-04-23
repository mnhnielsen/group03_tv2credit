package Creditsystem.Data;

import Creditsystem.Domain.IFileHandler;
import Creditsystem.Domain.ProducerController;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataLayer implements IFileHandler
{
    public ArrayList<String> readFile(String fileName) {
        String content = "";
        ArrayList<String> stringContent = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine())
            {
                content=reader.nextLine();
                stringContent.add(content+"\n");
            }
            reader.close();
            return stringContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringContent;
    }
    public  boolean writeToFile(Object obj, String fileName)
    {
        File file = new File(fileName);
        PrintWriter printWriter;
        try
        {
            printWriter = new PrintWriter(file);
            printWriter.println(obj.toString() + "\n");
            printWriter.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
