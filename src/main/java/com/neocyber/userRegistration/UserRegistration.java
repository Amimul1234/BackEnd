package com.neocyber.userRegistration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistration {
    private String emailId;
    private String password;
    private String fullName;
}
