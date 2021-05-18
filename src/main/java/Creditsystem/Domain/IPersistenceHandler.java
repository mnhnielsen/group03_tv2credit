package Creditsystem.Domain;

import javax.mail.Part;
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
    boolean createProduction(Production production);
    boolean createRole(Role role);
    boolean createCredit(Credits credits);
    ArrayList<Account> getUsers();
    int deleteUser(int userId);
    boolean createParticipant(Participant participant);
    int getProductionID();
    int getRoleID();
    int getParticipantID();
    boolean checkRoleName(String roleName);
    ArrayList<Participant> getParticipants();
    ArrayList<Role> getRoles();
    Participant getParticipant(String name);
    void getParticipantID(String name);
    void getRoleID(String name);

}
