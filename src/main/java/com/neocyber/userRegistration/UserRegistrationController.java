package com.neocyber.userRegistration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController( UserRegistrationService userRegistrationService ) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/registerNewUser")
    public void registerNewUser(@RequestBody  UserRegistration userRegistration)
    {
        userRegistrationService.registerNewUser(userRegistration);
    }

}
