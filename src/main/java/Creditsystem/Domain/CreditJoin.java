package Creditsystem.Domain;

public class CreditJoin
{
    private String name;
    private String roleName;
    private int releaseYear;

    public CreditJoin(String name, String roleName, int releaseYear)
    {
        this.name = name;
        this.roleName = roleName;
        this.releaseYear = releaseYear;
    }

    public String getName()
    {
        return name;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public int getReleaseYear()
    {
        return releaseYear;
    }
}
