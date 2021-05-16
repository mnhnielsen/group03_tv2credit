package Creditsystem.Domain;

public class ProducerAccount extends Account
{
    private String name;
    private String email;
    private int phoneNumber;
    private String company;

    public ProducerAccount(String username, String password, boolean isAdmin, String name, String email, int phoneNumber, String company)
    {
        super(username, password, isAdmin);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getCompany()
    {
        return company;
    }
}
