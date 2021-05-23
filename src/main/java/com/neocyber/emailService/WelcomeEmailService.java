package com.neocyber.emailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class WelcomeEmailService {

    @Value("${mail.userName}")
    private String userName;
    @Value("${mail.password}")
    private String password;

    private final MailContentBuilder mailContentBuilder;

    public WelcomeEmailService( MailContentBuilder mailContentBuilder ) {
        this.mailContentBuilder = mailContentBuilder;
    }

    @Async
    public void sendMail(String emailAddress, String clientName) throws MessagingException
    {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);}
        });


        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(userName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
        message.setSubject("Welcome to our shop");
        message.setSentDate(new Date());
        message.setText(mailContentBuilder.build(clientName));

        Transport.send(message);
    }
}
