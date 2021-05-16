package Creditsystem.Domain;

public interface IPersistenceHandler
{
    boolean logIn(String username, String password);
    boolean checkAuthorization(String username);
    boolean checkUsername(String username);
    boolean createAdminAccount(Account account);
    boolean createProducerAccount(ProducerAccount producerAccount);
    int getID(String username);
}
