package Creditsystem.Presentation;

import Creditsystem.Data.PersistenceHandler;
import Creditsystem.Domain.IPersistenceHandler;
import Creditsystem.Domain.Mail;

public class DBTest
{
    public static void main(String[] args)
    {
        IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();
       //Mail mail = new Mail();
        //mail.sendEmail("mnhnielsen@gmail.com","Ny konto fra Krediteringssystem", "Indhold");
    }
}
