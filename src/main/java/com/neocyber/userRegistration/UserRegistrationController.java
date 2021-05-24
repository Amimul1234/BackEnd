package com.neocyber.userRegistration;

import com.neocyber.emailService.WelcomeEmailService;
import com.neocyber.security.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;

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

    //Register new user
    @PostMapping("/registerNewUser")
    public void registerNewUser( @RequestBody UserRegistration userRegistration) {

        userRegistrationService.registerNewUser(userRegistration);
        try
        {
            welcomeEmailService.sendMail(userRegistration.getEmailId(), userRegistration.getFullName());
        }
        catch (MessagingException messagingException)
        {
            log.error("Error sending email: error is " + messagingException);
        }
    }

    //Get Jwt Token
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody UserRegistration userRegistration) throws Exception {

        userRegistrationService.authenticate(userRegistration.getEmailId(), userRegistration.getPassword());

        final UserDetails userDetails = userRegistrationService.loadUserByUsername(userRegistration.getEmailId());
        final String token = userRegistrationService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/hello")
    public String getMsg()
    {
        return "Hello World";
    }

}
