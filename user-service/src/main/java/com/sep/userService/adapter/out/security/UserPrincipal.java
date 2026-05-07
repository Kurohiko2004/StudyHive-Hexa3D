package com.sep.userService.adapter.out.security;

import com.sep.commonModule.domain.model.EntityStatus;
import com.sep.userService.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Map your domain User's role to Spring Security's GrantedAuthority
        // Assuming your User domain model has a getRole() method that returns an Enum or String
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public String getPassword() {
        // Return the hashed password from your domain User
        return user.getPassword().value();
    }

    @Override
    public String getUsername() {
        // Return the email as the username for Spring Security
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implement your account expiration logic here if any
        return true; // For simplicity, always true
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implement your account locking logic here if any
        return true; // For simplicity, always true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement your credentials expiration logic here if any
        return true; // For simplicity, always true
    }

    @Override
    public boolean isEnabled() {
        // Implement your user enabled/disabled logic here if any
        // e.g., based on user.getStatus()
        return EntityStatus.ACTIVE.equals(user.getStatus());
    }
}
