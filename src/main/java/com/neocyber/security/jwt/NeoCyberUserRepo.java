package com.neocyber.security.jwt;

import com.neocyber.security.jwt.entity.NeoCyberUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface NeoCyberUserRepo extends JpaRepository<NeoCyberUser, String> {
    UserDetails findByEmailId( String s );
}
