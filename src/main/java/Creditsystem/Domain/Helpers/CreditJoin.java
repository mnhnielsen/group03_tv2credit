package Creditsystem.Domain.Helpers;

public class CreditJoin
{
    private String name;
    private String roleName;
    private int releaseYear;

    public CreditJoin(String name, String roleName, int releaseYear)
    {
        this.setName(name);
        this.setRoleName(roleName);
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

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
}
