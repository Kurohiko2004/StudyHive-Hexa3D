package com.sep.userService.config;

import com.sep.userService.application.port.in.RegisterUserUseCase;
import com.sep.userService.application.port.out.PasswordEncoderRepository;
import com.sep.userService.application.port.out.UserRepository;
import com.sep.userService.application.usecase.RegisterUserUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {

    private final UserRepository userRepository;
    private final PasswordEncoderRepository passwordEncoderRepository;

    @Bean
    public RegisterUserUseCase registerUserUseCase() {
        return new RegisterUserUseCaseImpl(userRepository, passwordEncoderRepository);
    }
}
