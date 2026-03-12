package com.sep.commonModule.application.usecase;

import com.sep.commonModule.application.command.RegisterUserCommand;
import com.sep.commonModule.application.port.in.RegisterUserUseCase;
import com.sep.commonModule.application.port.out.UserRepository;
import com.sep.commonModule.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
    // inject the outbound port (UserRepository)
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User execute(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new IllegalArgumentException("User with email " + command.getEmail() + " already exists.");
        }

        String encodedPassword = passwordEncoder.encode(command.getPassword());

        User newUser = User.createNew(
                command.getEmail(),
                encodedPassword,
                command.getFullName());

        // business rule validation, needed during use case execution
        newUser.validate();
        return userRepository.save(newUser);
    }
}
