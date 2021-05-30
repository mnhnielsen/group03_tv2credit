package Creditsystem.Domain.Model;

import java.util.List;

public class Production
{
    private int id;
    private String title;
    private String description;
    private int releaseYear;
    private int createdby;
    private boolean isPublished;


    public Production(String title, int releaseYear, String description, int createdby, boolean isPublished)
    {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.createdby = createdby;
        this.isPublished = isPublished;
    }
    public Production(int id, String title, int releaseYear, String description, int createdby, boolean isPublished)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.createdby = createdby;
        this.isPublished = isPublished;
    }
    public Production(int id){
        this.id = id;
    }
    public Production(int id, String title){
        this.id = id;
        this.title = title;
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

    public boolean isPublished()
    {
        return isPublished;
    }
}
