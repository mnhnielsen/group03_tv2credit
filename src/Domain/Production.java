package Domain;

import java.util.ArrayList;
import java.util.List;

public class Production
{
    int id;
    String title;
    String description;
    int releaseYear;
    List<Credit> creditList = new ArrayList<>();

    public Production(int id, String title, String description, int releaseYear, List<Credit> creditList)
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

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getReleaseYear()
    {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = releaseYear;
    }

    public List<Credit> getCreditList()
    {
        return creditList;
    }

    public void setCreditList(List<Credit> creditList)
    {
        this.creditList = creditList;
    }



}
