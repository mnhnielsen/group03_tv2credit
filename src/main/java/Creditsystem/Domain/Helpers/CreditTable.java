package Creditsystem.Domain.Helpers;

import Creditsystem.Domain.Participant;

public class CreditTable
{
    private int id;
    private String role;
    private Participant participant;
    private String name;

    public CreditTable(Participant participant, String role, String name)
    {
        this.participant = participant;
        this.role = role;
        this.name = name;
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

    public String getName()
    {
        return name;
    }
}
