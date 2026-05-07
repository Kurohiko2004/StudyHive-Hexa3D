package com.sep.learningContents.config.security;

import com.sep.commonModule.security.BaseJwtAuthFilter;
import com.sep.commonModule.security.JwtProvider;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthFilter extends BaseJwtAuthFilter {
    public JwtAuthFilter(JwtProvider jwtProvider) {
        super(jwtProvider);
    }
}
