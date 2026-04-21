package com.sep.userService.application.usecase;

import com.sep.userService.adapter.in.web.dto.LoginResponse;
import com.sep.userService.application.command.LoginCommand;
import com.sep.userService.application.port.in.LoginUseCase;
import com.sep.userService.application.port.out.PasswordEncoderRepository;
import com.sep.userService.application.port.out.UserRepository;
import com.sep.userService.config.security.JwtUtils;
import com.sep.userService.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoderRepository passwordEncoderRepository;
    private final JwtUtils jwtUtils;

    @Override
    public LoginResponse execute(LoginCommand command) {
        // 1. Find User by Email
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        // 2. Validate Password
        if (!passwordEncoderRepository.matches(command.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // 3. Generate Token
        org.springframework.security.core.userdetails.User springUser =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getStatus().name()))
                );

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getFullName());

        String accessToken = jwtUtils.generateToken(claims, springUser);

        // 4. Return Response
        return LoginResponse.builder()
                .accessToken(accessToken)
                .userId(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getStatus().name())
                .build();
    }
}
