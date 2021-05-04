package Creditsystem.Domain;

public class Participant
{
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    public Participant(int id, String name, String role, String phoneNumber, String email)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Participant(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
