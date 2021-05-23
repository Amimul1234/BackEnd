package com.neocyber.userRegistration;

import com.neocyber.emailService.WelcomeEmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;

@RestController
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
    public void registerNewUser(@RequestBody  UserRegistration userRegistration)
            throws MessagingException {
        userRegistrationService.registerNewUser(userRegistration);
        welcomeEmailService.sendMail(userRegistration.getEmailId(), userRegistration.getFullName());
    }

}
