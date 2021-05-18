package Creditsystem.Domain;

public class Participant
{
    private int id;
    private String name;
    private int phoneNumber;
    private String email;

    public Participant(String name, int phoneNumber, String email)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Participant(int id)
    {
        this.id = id;
    }

    public Participant(String name, String email, int Phone){
        this.name = name;
        this.email = email;
        this.phoneNumber = Phone;
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

    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }
}
