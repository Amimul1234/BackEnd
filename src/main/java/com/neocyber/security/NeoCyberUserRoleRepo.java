package com.neocyber.security;

import com.neocyber.security.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeoCyberUserRoleRepo extends JpaRepository<UserRole, Long> {
}
