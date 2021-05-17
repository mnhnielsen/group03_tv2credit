package Creditsystem.Domain;

public class Credits
{


    private int productionId;
    private int roleId;
    private int participantId;

    public Credits(int productionId, int roleId, int participantId)
    {
        this.productionId = productionId;
        this.roleId = roleId;
        this.participantId = participantId;
    }

    public int getProductionId()
    {
        return productionId;
    }

    public int getRoleId()
    {
        return roleId;
    }

    public int getParticipantId()
    {
        return participantId;
    }
}
