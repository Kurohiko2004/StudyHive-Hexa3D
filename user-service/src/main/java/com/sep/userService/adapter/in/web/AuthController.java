package com.sep.userService.adapter.in.web;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.userService.adapter.in.web.dto.LoginResponse;
import com.sep.userService.adapter.in.web.dto.UserResponse;
import com.sep.userService.adapter.out.security.UserPrincipal;
import com.sep.userService.application.command.LoginCommand;
import com.sep.userService.application.command.RegisterUserCommand;
import com.sep.userService.application.port.in.LoginUseCase;
import com.sep.userService.application.port.in.RegisterUserUseCase;
import com.sep.userService.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserResponse>> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        UserResponse response = registerUserUseCase.execute(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(BaseResponse.success(response, "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@Valid @RequestBody LoginCommand command) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.getEmail(),
                        command.getPassword())
        );

        // 2. Lấy domain User từ Principal
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        User authenticatedUser = principal.getUser();

        // 3. Use Case chỉ làm việc hậu xác thực: tạo token và build response.
        LoginResponse response = loginUseCase.execute(authenticatedUser);
        return ResponseEntity.ok(BaseResponse.success(response, "Login successful"));
    }
}
