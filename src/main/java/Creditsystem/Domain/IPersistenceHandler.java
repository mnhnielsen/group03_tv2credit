package Creditsystem.Domain;

public interface IPersistenceHandler
{
    boolean logIn(String username, String password);
    boolean isAdmin(String username);
    boolean checkAuthorization(String username);
    boolean createAdminAccount(Account account);
    boolean createProducerAccount(ProducerAccount producerAccount);
}
