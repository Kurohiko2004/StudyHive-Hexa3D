package com.sep.userService.adapter.out.security;

import com.sep.commonModule.security.JwtProvider;
import com.sep.userService.application.port.out.TokenGenerator;
import com.sep.userService.domain.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenAdapter implements TokenGenerator {
    private final JwtProvider jwtProvider;

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getFullName());
        claims.put("role", user.getRole().name());

        return jwtProvider.createToken(claims, user.getEmail());
    }
}
