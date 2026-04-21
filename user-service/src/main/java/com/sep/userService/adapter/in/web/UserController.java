package com.sep.userService.adapter.in.web;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.userService.adapter.in.web.dto.LoginRequest;
import com.sep.userService.adapter.in.web.dto.LoginResponse;
import com.sep.userService.adapter.in.web.dto.UserResponse;
import com.sep.userService.application.command.LoginCommand;
import com.sep.userService.application.command.RegisterUserCommand;
import com.sep.userService.application.port.in.LoginUseCase;
import com.sep.userService.application.port.in.RegisterUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

// receive client requests
public class UserController {
    // DI
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserResponse>> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        // Spring injects an implementation of RegisterUserUseCase (RegisterUserUseCaseImpl)
        // into the constructor of UserController. At runtime, calling registerUserUseCase.execute
        // will actually call RegisterUserUseCaseImpl.execute()
        UserResponse response = registerUserUseCase.execute(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(location)
                .body(BaseResponse.success(response, "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@Valid @RequestBody LoginCommand command) {
        LoginResponse response = loginUseCase.execute(command);
        // Return 200 OK with the token
        return ResponseEntity.ok(BaseResponse.success(response, "Login successful"));
    }
}

// Controller - UC - Domain - DTO - Response
