package com.sep.userService.config;

import com.sep.commonModule.security.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    // Dùng interface UserDetailsService. Sau này có thể thay để load user từ Redis,
    // gọi gRPC, OAuth..
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 1. Validate authHeader, then extract token and claims
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);
        String username = jwtProvider.extractUsername(token);

        // 2. Validate username and token, then load user from db
        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 3. validate token, then create an authenticated token for this user by
        // passing userDetails into it;
        // finally, add to SecurityContextHolder
        if (jwtProvider.validateToken(token, userDetails.getUsername())) {
            UsernamePasswordAuthenticationToken authenticatedToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            authenticatedToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // unimportant
            SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
        }

        filterChain.doFilter(request, response);
    }
}
