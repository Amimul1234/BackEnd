package com.neocyber.emailService;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.*;
import java.util.Date;

@Service
public class WelcomeEmailService {

    private final MailContentBuilder mailContentBuilder;
    private final JavaMailSender javaMailSender;

    public WelcomeEmailService( MailContentBuilder mailContentBuilder,
                                JavaMailSender javaMailSender ) {
        this.mailContentBuilder = mailContentBuilder;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMail(String emailAddress, String clientName) throws MessagingException
    {
        MimeMessagePreparator messagePreparator = mimeMessage -> {

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

            messageHelper.setFrom("neocybermr2021@gmail.com");
            messageHelper.setTo(emailAddress);
            messageHelper.setSubject("Welcome to our shop");
            messageHelper.setSentDate(new Date());

            String content = mailContentBuilder.build(clientName);
            messageHelper.setText(content, true);

        };

        javaMailSender.send(messagePreparator);
    }
}
