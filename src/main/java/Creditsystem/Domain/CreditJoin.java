package Creditsystem.Domain;

public class CreditJoin
{
    private String name;
    private String roleName;

    public CreditJoin(String name, String roleName)
    {
        this.name = name;
        this.roleName = roleName;
    }

    public String getName()
    {
        return name;
    }

    public String getRoleName()
    {
        return roleName;
    }
}
