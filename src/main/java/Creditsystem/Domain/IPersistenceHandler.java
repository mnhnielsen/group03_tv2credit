package Creditsystem.Domain;

import java.util.ArrayList;

public interface IPersistenceHandler
{
    boolean logIn(String username, String password);
    boolean checkAuthorization(String username);
    boolean checkUsername(String username);
    boolean createAdminAccount(Account account);
    boolean createProducerAccount(ProducerAccount producerAccount);
    int getID(String username);
    ArrayList getProductions();
    Production getProductionTitle(String title);
    ArrayList<CreditJoin> getCredits(String title);
}
