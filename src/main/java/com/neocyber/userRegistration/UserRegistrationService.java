package com.neocyber.userRegistration;

import com.neocyber.exception.AlreadyExists;
import com.neocyber.security.NeoCyberUserRepo;
import com.neocyber.security.entity.NeoCyberUser;
import com.neocyber.security.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.neocyber.security.ApplicationUserRole.ROLE_USER;

@Service
@Slf4j
public class UserRegistrationService {

    private final NeoCyberUserRepo neoCyberUserRepo;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService( NeoCyberUserRepo neoCyberUserRepo,
                                    PasswordEncoder passwordEncoder ) {
        this.neoCyberUserRepo = neoCyberUserRepo;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerNewUser( UserRegistration userRegistration ) {

        Optional<NeoCyberUser> neoCyberUserOptional =
                neoCyberUserRepo.findById(userRegistration.getEmailId());

        if(neoCyberUserOptional.isPresent())
        {
            log.info("User with email: " +userRegistration.getEmailId() + " already exists");
            throw new AlreadyExists("User Already Exists");
        }
        else
        {
            NeoCyberUser neoCyberUser = new NeoCyberUser();

            neoCyberUser.setEmailId(userRegistration.getEmailId());
            neoCyberUser.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
            neoCyberUser.setFullName(userRegistration.getFullName());
            neoCyberUser.setEnabled(true);

            UserRole userRole = new UserRole();
            userRole.setRole(ROLE_USER.name());
            userRole.setNeoCyberUser(neoCyberUser);

            neoCyberUser.addNewRole(userRole);

            neoCyberUserRepo.save(neoCyberUser);
        }
    }
}
