package com.sep.userService.application.usecase;

import com.sep.userService.adapter.in.web.dto.LoginResponse;
import com.sep.userService.application.port.in.LoginUseCase;
import com.sep.userService.application.port.out.TokenGenerator;
import com.sep.userService.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final TokenGenerator tokenGenerator;

    @Override
    public LoginResponse execute(User authenticatedUser) {
        // Spring Security đã authenticate() trước khi use case này được gọi.
        // Nhiệm vụ duy nhất ở đây: tạo token và trả về thông tin user.
        String accessToken = tokenGenerator.generateToken(authenticatedUser);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .userId(authenticatedUser.getId())
                .email(authenticatedUser.getEmail())
                .fullName(authenticatedUser.getFullName())
                .role(authenticatedUser.getRole().name())
                .build();
    }
}
