package Creditsystem.Domain;

import java.util.List;

public class Production
{
    private int id;
    private String title;
    private String description;
    private int releaseYear;
    private int createdby;


    public Production(String title, int releaseYear, String description, int createdby)
    {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.createdby = createdby;
    }
    public Production(int id){
        this.id = id;
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

    @Override
    public String toString()
    {
        return "Title: " + title + "\n"
                + "Release year: " + getReleaseYear() + "\n"
                + "Description: " + getDescription();
                //+ "Credits: " + "\n" + getCreditList().toString();
    }

    public int getId()
    {
        return id;
    }

    public int getCreatedby()
    {
        return createdby;
    }
}
