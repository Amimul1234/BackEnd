package com.neocyber.userRegistration;

import com.neocyber.emailService.WelcomeEmailService;
import com.neocyber.security.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/user/")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final WelcomeEmailService welcomeEmailService;


    public UserRegistrationController( UserRegistrationService userRegistrationService, WelcomeEmailService welcomeEmailService) {
        this.userRegistrationService = userRegistrationService;
        this.welcomeEmailService = welcomeEmailService;
    }

//    @PostMapping("/registerNewUser")
//    public void registerNewUser(@RequestBody  UserRegistration userRegistration) {
//        userRegistrationService.registerNewUser(userRegistration);
//
//        try
//        {
//            welcomeEmailService.sendMail(userRegistration.getEmailId(),
//                    userRegistration.getFullName());
//        }
//        catch (MessagingException messagingException)
//        {
//            log.error("Error sending email: error is " + messagingException);
//        }
//    }



    @PostMapping("/registerNewUser")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody UserRegistration userRegistration) {
        return userRegistrationService.registerNewUser(userRegistration);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/hello")
    public String getMsg()
    {
        return "Hello World";
    }

}
