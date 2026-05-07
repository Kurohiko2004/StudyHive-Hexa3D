package com.sep.userService.adapter.out.security;

import com.sep.userService.application.port.out.PasswordEncoderRepository;
import com.sep.userService.domain.valueobject.HashedPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderAdapter implements PasswordEncoderRepository {

    // dependency injection (can swap actual encoder: BCrypt, Argon2)
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(CharSequence rawPassword) {

        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, HashedPassword encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword.value());
    }
}
