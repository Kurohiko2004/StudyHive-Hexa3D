package com.sep.userService.adapter.out.security;

import com.sep.userService.application.port.out.UserRepository;
import com.sep.userService.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<User> userInfo = userRepository.findByEmail(username);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        User user = userInfo.get();

        // Map your domain User to Spring Security's UserDetails object via
        // UserPrincipal
        return new UserPrincipal(user);
    }
}
