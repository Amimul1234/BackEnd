package com.neocyber.userRegistration;

import com.neocyber.emailService.WelcomeEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;

@RestController
@Slf4j
@RequestMapping("/api/v1/user/")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final WelcomeEmailService welcomeEmailService;

    public UserRegistrationController( UserRegistrationService userRegistrationService,
                                       WelcomeEmailService welcomeEmailService ) {
        this.userRegistrationService = userRegistrationService;
        this.welcomeEmailService = welcomeEmailService;
    }

    @PostMapping("/registerNewUser")
    public void registerNewUser(@RequestBody  UserRegistration userRegistration) {
        userRegistrationService.registerNewUser(userRegistration);

        try
        {
            welcomeEmailService.sendMail(userRegistration.getEmailId(),
                    userRegistration.getFullName());
        }
        catch (MessagingException messagingException)
        {
            log.error("Error sending email: error is " + messagingException);
        }
    }

}
