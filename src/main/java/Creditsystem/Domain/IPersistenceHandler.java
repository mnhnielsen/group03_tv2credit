package Creditsystem.Domain;

public interface IPersistenceHandler
{
    public boolean logIn(String username, String password);
    public boolean isAdmin(String username);
    public boolean checkUsername(String username);
    public boolean createAdminAccount(Account account);
    public boolean createProducerAccount(ProducerAccount producerAccount);
}
