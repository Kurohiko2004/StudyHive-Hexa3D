package com.sep.userService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;

@Configuration
// @Configuration là một Annotation đánh dấu trên một Class cho phép Spring Boot
// biết được
// đây là nơi định nghĩa ra các Bean.
@EnableWebSecurity // Bật Spring Security cho toàn bộ app.
@EnableMethodSecurity // Bật bảo mật phương thức
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter,
            UserDetailsService userDetailsService,
            AuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/users/register",
                                "/api/v1/users/login",
                                "/swagger-ui/**",
                                "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/teachers/**").hasRole("TEACHER")
                        .requestMatchers("/api/v1/students/**").hasRole("STUDENT")
                        .anyRequest().authenticated()
                )
                // Stateless session (required for JWT)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Set custom authentication provider
                .authenticationProvider(authenticationProvider())

                // Add JWT filter before Spring Security's default filter
                .addFilterBefore(jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    // provide the actual implementation of PasswordEncoder (could be BCrypt,
    // Argon2)
    // All of these encoder classes (BCrypt, Argon2) implements the PasswordEncoder
    // interface
    @Bean
    public PasswordEncoder passwordEncoder() {
        // we can swap the actual encoder (implmentation) by changing the return code
        // here.
        // Any class that needs to encode or verify passwords (like your
        // LoginUseCaseImpl or
        // RegisterUserUseCaseImpl via PasswordEncoderRepository) only depends on the
        // PasswordEncoder
        // interface. It doesn't care if the actual
        // implementation is BCryptPasswordEncoder, Argon2PasswordEncoder, or any other.

        // Example: To use Argon2PasswordEncoder
        // return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

        // Example: To use PBKDF2PasswordEncoder
        // return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_fips256();
        return new BCryptPasswordEncoder();
    }

    /*
     * Authentication provider configuration
     * Links UserDetailsService and PasswordEncoder
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // create an AuthenticationManager
        return config.getAuthenticationManager();
    }

    @Bean
    public GrpcAuthenticationReader grpcAuthenticationReader() {
        // Sử dụng Lambda để tránh lỗi Constructor của AnonymousAuthenticationReader
        // Tạm thời trả về null để báo hiệu chưa có cơ chế xác thực cho gRPC
        return (call, headers) -> null;
    }
}
