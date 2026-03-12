package com.sep.commonModule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// @Configuration là một Annotation đánh dấu trên một Class cho phép Spring Boot biết được
// đây là nơi định nghĩa ra các Bean.
@EnableWebSecurity
// → Bật Spring Security cho toàn bộ app.

public class SecurityConfig {

    // this is where we define which endpoints can be accessed without auth, which needs
    // login, ...
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF để test API dễ dàng hơn
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/users/register").permitAll() // Cho phép truy cập không cần login
                        .anyRequest().authenticated() // Các request khác vẫn cần login
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}