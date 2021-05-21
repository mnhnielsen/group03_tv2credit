package Creditsystem.Domain;

public class Account
{
    private int id;
    private String username;
    private String password;
    private boolean isadmin;

    public Account(String username, String password, boolean isadmin)
    {
        this.username = username;
        this.password = password;
        this.isadmin = isadmin;
    }

    public Account(String username, boolean isadmin)
    {
        this.username = username;
        this.isadmin = isadmin;
    }

    public Account(int id, String username)
    {
        this.id = id;
        this.username = username;
    }

    public Account(int id, String username, String password, boolean isadmin)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isadmin = isadmin;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean getAdminStatus()
    {
        return isadmin;
    }

    public int getId()
    {
        return id;
    }
}
