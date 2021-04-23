package Creditsystem.Domain;

public class Credit
{
    private Participant participant;
    private String role;
    private String name;

    public Credit(Participant participant, String role, String name)
    {
        this.participant = participant;
        this.role = role;
        this.name = name;
    }

    public String getName()
    {
        return participant.getName();
    }

    public Participant getParticipant()
    {
        return participant;
    }

    public String getRole()
    {
        return role;
    }

    @Override
    public String toString()
    {
        return "Role: " + role + ", name: " + getParticipant().getName() + "\n";
    }
}
