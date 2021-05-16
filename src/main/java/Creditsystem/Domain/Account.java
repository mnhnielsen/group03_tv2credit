package Creditsystem.Domain;

public class Account
{
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;

    public Account(String username, String password, boolean isAdmin)
    {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Account(String username, boolean isAdmin)
    {
        this.username = username;
        this.isAdmin = isAdmin;
    }
    public Account(int id, String username){
        this.id = id;
        this.username = username;
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
        return isAdmin;
    }

    public int getId()
    {
        return id;
    }
}
