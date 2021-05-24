package com.neocyber.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NeoCyberUserDetailsService implements UserDetailsService {

    private final NeoCyberUserRepo neoCyberUserRepo;

    public NeoCyberUserDetailsService( NeoCyberUserRepo neoCyberUserRepo ) {
        this.neoCyberUserRepo = neoCyberUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername( String s ) throws UsernameNotFoundException {
        return neoCyberUserRepo.findByEmailId(s);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }
}
