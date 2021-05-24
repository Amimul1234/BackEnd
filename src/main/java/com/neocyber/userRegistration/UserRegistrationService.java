package com.neocyber.userRegistration;

import com.neocyber.exception.AlreadyExists;
import com.neocyber.security.JwtUtils;
import com.neocyber.security.NeoCyberUserRepo;
import com.neocyber.security.entity.NeoCyberUser;
import com.neocyber.security.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.neocyber.security.ApplicationUserRole.ROLE_USER;

@Service
@Slf4j
public class UserRegistrationService {

    private final NeoCyberUserRepo neoCyberUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserRegistrationService( NeoCyberUserRepo neoCyberUserRepo, PasswordEncoder passwordEncoder,
                                    JwtUtils jwtUtils, AuthenticationManager authenticationManager ) {
        this.neoCyberUserRepo = neoCyberUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
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

    public UserDetails loadUserByUsername( String emailId ) {
        return neoCyberUserRepo.findByEmailId(emailId);
    }

    public String generateToken( UserDetails userDetails ) {
        return jwtUtils.generateToken(userDetails);
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
