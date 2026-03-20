package com.sep.userService.application.usecase;

import com.sep.commonModule.exception.BusinessValidationException;
import com.sep.userService.adapter.in.web.dto.UserResponse;
import com.sep.userService.application.command.RegisterUserCommand;
import com.sep.userService.application.port.in.RegisterUserUseCase;
import com.sep.userService.application.port.out.PasswordEncoderRepository;
import com.sep.userService.application.port.out.UserRepository;
import com.sep.userService.domain.model.User;
import com.sep.userService.domain.valueobject.Password;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
    // inject the outbound port
    private final UserRepository userRepository;
    private final PasswordEncoderRepository passwordEncoderRepository;

    @Override
    @Transactional // TODO: tìm hiểu cái này để làm gì!
    public UserResponse execute(RegisterUserCommand command) {
        // 1.1 Validate the raw password (business rule validation)
        Password validatedPassword = Password.createNew(command.getPassword());

        // 1.2 Validate email uniqueness
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new BusinessValidationException("email", "User with email " + command.getEmail() + " already exists.");
        }

        // 2. Encode the validated password
        String encodedPassword = passwordEncoderRepository.encode(validatedPassword.value());

        // 3. Create the new User entity with the encoded password
        User newUser = User.createNew(
                command.getEmail(),
                encodedPassword,
                command.getFullName());

        // 5. Perform final entity-level validation (non-empty checks)
        newUser.validate();
        
        // 6. Save the new user
        return userRepository.save(newUser);
    }
}
