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

    //TODO This was edited for iteration 1. Should be revised for next iteration!
    @Override
    public String toString()
    {
        return "Rolle: " + role +"\n" + "Navn: " + getParticipant().getName() + "\n" + "\n";
    }
}
