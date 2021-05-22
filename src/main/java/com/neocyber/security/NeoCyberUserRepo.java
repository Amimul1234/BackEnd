package com.neocyber.security;

import com.neocyber.security.entity.NeoCyberUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface NeoCyberUserRepo extends JpaRepository<NeoCyberUser, String> {
    UserDetails findByEmailId( String s );
}
