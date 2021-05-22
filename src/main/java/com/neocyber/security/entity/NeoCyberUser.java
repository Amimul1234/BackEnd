package com.neocyber.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class NeoCyberUser implements UserDetails {
    @Id
    private String emailId;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;
    @Column(nullable = false)
    private String fullName;
    private boolean enabled;

    @OneToMany(mappedBy = "neoCyberUser", orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<UserRole> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();

        roles.forEach(userRole -> simpleGrantedAuthorityList.add(
                new SimpleGrantedAuthority(userRole.getRole())));

        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void addNewRole(String userRole)
    {
        UserRole userRole1 = new UserRole();

        userRole1.setRole(userRole);
        userRole1.setNeoCyberUser(this);

        roles.add(userRole1);
    }
}
