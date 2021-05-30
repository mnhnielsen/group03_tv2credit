package Creditsystem.Domain;

import Creditsystem.Domain.Helpers.CreditJoin;
import Creditsystem.Domain.Model.*;

import java.util.ArrayList;

public interface IPersistenceHandler
{
    boolean logIn(String username, String password);
    boolean checkAuthorization(String username);
    boolean checkUsername(String username);
    boolean createAdminAccount(Account account);
    boolean createProducerAccount(Producer producerAccount);
    int getAccountID(String username);
    ArrayList getProductions();
    Production getProductionTitle(String title);
    ArrayList<CreditJoin> getCredits(String title);
    boolean createProduction(Production production);
    boolean createRole(Role role);
    boolean createCredit(Credit credit);
    ArrayList<Account> getUsers();
    boolean deleteUser(String userName);
    boolean createParticipant(Participant participant);
    int getProductionID();
    int getRoleID();
    int getParticipantID();
    ArrayList<Participant> getParticipants();
    ArrayList<Role> getRoles();
    Participant getParticipant(String name);
    Participant getParticipantID(String name);
    Role getRoleID(String name);
    Producer getProducerAccount(int id);
    Account getAccount(int id);
    ArrayList<Production> getMyProductions(int id);
    ArrayList<Production> getUnreleasedProductions();
    boolean releaseProduction(Production production);
    boolean changeAccountPhone(Producer account);
    boolean changeAccountEmail(Producer account);
    boolean changeAccountName(Producer account);
    boolean changeAccountPassword(Producer account);
    boolean changeAccountUsername(Account account);
    boolean changeAccountPassword(Account account);
    boolean checkAccountEmail(String email);
    boolean checkPhone(int phone);
}
