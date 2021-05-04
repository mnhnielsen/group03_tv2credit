package Creditsystem.Domain;

import java.util.List;

public class Production
{
    private int id;
    private String title;
    private String description;
    private int releaseYear;
    private List<Credit> creditList;

    public Production(int id, String title, String description, int releaseYear, List<Credit> creditList)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.creditList = creditList;
    }

    public Production(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public List<Credit> getCreditList()
    {
        return creditList;
    }

    @Override
    public String toString()
    {
        return "Title: " + title + "\n"
                + "Release year: " + releaseYear + "\n"
                + "Description: " + description + "\n"
                + "Credits: " + "\n" + getCreditList().toString();
    }

}
