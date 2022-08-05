package by.vlad.library.util.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger();

    private static final String MAIL_PROPERTIES_FILE = "config/mail.properties";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    private static MailSessionFactory factory;
    private static MailSender instance;

    static{
        Properties sessionProperties = new Properties();
        try(InputStream mailPropertiesInputStream = MailSessionFactory.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES_FILE)){
            sessionProperties.load(mailPropertiesInputStream);
            factory = new MailSessionFactory(sessionProperties);
        }catch (IOException e){
            logger.error("Failed to read mail properties file: " + MAIL_PROPERTIES_FILE);
        }
    }

    private MailSender(){}

    public static MailSender getInstance(){
        if (instance == null){
            instance = new MailSender();
        }

        return instance;
    }

    public boolean sendMail(String recipient, String subject, String text){
        try {
            Session session = factory.createSession();
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(text, CONTENT_TYPE);
            Transport.send(message);
            logger.info("Message sent to " + recipient);
            return true;
        } catch (AddressException e) {
            logger.error("Invalid recipient address " + recipient + ", " + e.getMessage());
            return false;
        }catch (MessagingException e){
            logger.error("Messaging exception " + e.getMessage());
            return false;
        }
    }

    public boolean sendMails(List<String> recipients, String subject, String text){
        try{
            Session session = factory.createSession();
            MimeMessage message = new MimeMessage(session);
            recipients.forEach(recipient -> {
                try{
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                } catch (AddressException e) {
                    logger.error("Invalid recipient address " + recipient + ", " + e.getMessage());
                } catch (MessagingException e) {
                    logger.error("Messaging exception " + e.getMessage());
                }
            });
            message.setSubject(subject);
            message.setContent(text, CONTENT_TYPE);
            Transport.send(message);
            logger.info("Message sent to " + recipients.toString());
            return true;
        }catch (MessagingException e){
            logger.error("Messaging exception " + e.getMessage());
            return false;
        }
    }

}

