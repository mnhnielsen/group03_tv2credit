package Creditsystem.Domain;

import java.util.ArrayList;
import java.util.List;

public class Production
{
    private int id;
    private String title;
    private String description;
    private int releaseYear;
    private ArrayList<Credit> creditList;

    public Production(int id, String title, String description, int releaseYear, ArrayList<Credit> creditList)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.creditList = creditList;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public int getReleaseYear()
    {
        return releaseYear;
    }

    public ArrayList<Credit> getCreditList()
    {
        return creditList;
    }

}
