package Creditsystem.Domain;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Mail {

    public void sendEmail() {
// Recipient's email ID needs to be mentioned.
        String to = "mnhnielsen@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "krediteringssystem@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("krediteringssystem@gmail.com", "Credit@123");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(false);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Ny konto hos Krediteringssytem");

            // Now set the actual message
            message.setText("Hej navn." + "\n" + "Din nye konto hos Krediteringssystemet er klar. Dit brugernavn er x og din midlertidig adgangskode er x." + "\n" + "Du kan ændre din adgangskode når du har logget ind første gang.");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}

