package Creditsystem.Data;

import Creditsystem.Domain.ProducerController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataLayer
{
    public static ArrayList<String> readFile(String fileName) {
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
}
