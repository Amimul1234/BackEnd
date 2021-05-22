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
    private boolean enabled = true;

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
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void addNewRole(UserRole userRole)
    {
        if(roles.size() == 0 )
        {
            roles = new ArrayList<>();
            roles.add(userRole);
        }

        roles.add(userRole);
    }
}
