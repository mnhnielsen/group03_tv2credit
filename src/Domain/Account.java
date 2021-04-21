package Domain;

public class Account
{
    //This was named "Producer" on class diagram.
    private int id;
    private String username;
    private String password;

    public Account(int id, String username, String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public int getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
