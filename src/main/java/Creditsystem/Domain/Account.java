package Creditsystem.Domain;

public class Account
{
    //private int id;
    private String username;
    private String password;
    private boolean isAdmin;

    public Account(String username, String password, boolean isAdmin)
    {
        //his.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Account(String username, boolean isAdmin)
    {
        this.username = username;
        this.isAdmin = isAdmin;
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
}
