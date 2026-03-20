package com.sep.userService.adapter.in.web;

import com.sep.userService.application.command.RegisterUserCommand;
import com.sep.userService.application.port.in.RegisterUserUseCase;
import com.sep.commonModule.dto.BaseResponse;
import com.sep.userService.adapter.in.web.dto.UserResponse;
import com.sep.userService.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

// receive client requests
public class UserController {
    // DI
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserResponse>> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        // Spring injects an implementation of RegisterUserUseCase (RegisterUserUseCaseImpl)
        // into the constructor of UserController. At runtime, calling registerUserUseCase.execute
        // will actually call RegisterUserUseCaseImpl.execute()
        UserResponse response = registerUserUseCase.execute(command);

        URI location = URI.create("/api/users/" + response.getId());
        return ResponseEntity.created(location) // Trả về 201 Created
                .body(BaseResponse.success(response, "User registered successfully"));    }
}

// Controller - UC - Domain - DTO - Response
