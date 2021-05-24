package com.neocyber.security.jwt;

import com.neocyber.security.jwt.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeoCyberUserRoleRepo extends JpaRepository<UserRole, Long> {
}
