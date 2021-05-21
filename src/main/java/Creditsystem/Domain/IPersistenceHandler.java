package Creditsystem.Domain;

import javax.mail.Part;
import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IPersistenceHandler
{
    boolean logIn(String username, String password);
    boolean checkAuthorization(String username);
    boolean checkUsername(String username);
    boolean createAdminAccount(Account account);
    boolean createProducerAccount(ProducerAccount producerAccount);
    int getAccountID(String username);
    ArrayList getProductions();
    Production getProductionTitle(String title);
    ArrayList<CreditJoin> getCredits(String title);
    boolean createProduction(Production production);
    boolean createRole(Role role);
    boolean createCredit(Credits credits);
    ArrayList<Account> getUsers();
    boolean deleteUser(String userName);
    boolean createParticipant(Participant participant);
    int getProductionID();
    int getRoleID();
    int getParticipantID();
    boolean checkRoleName(String roleName);
    ArrayList<Participant> getParticipants();
    ArrayList<Role> getRoles();
    Participant getParticipant(String name);
    Participant getParticipantID(String name);
    Role getRoleID(String name);
    ProducerAccount getProducerAccount(int id);
    Account getAccount(int id);
    ArrayList<Production> getMyProductions(int id);
    ArrayList<Production> getUnreleasedProductions();
    boolean releaseProduction(Production production);
    boolean deleteCredit(String title);
    boolean changeAccountPhone(ProducerAccount account);
    boolean changeAccountEmail(ProducerAccount account);
    boolean changeAccountName(ProducerAccount account);
    boolean changeAccountPassword(ProducerAccount account);
    boolean changeAccountUsername(Account account);
    boolean changeAccountPassword(Account account);
}
