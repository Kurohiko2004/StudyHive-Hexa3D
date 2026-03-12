package com.sep.commonModule.adapter.in.web;

import com.sep.commonModule.adapter.in.web.dto.RegisterUserRequest;
import com.sep.commonModule.adapter.in.web.dto.UserDTO;
import com.sep.commonModule.application.command.RegisterUserCommand;
import com.sep.commonModule.application.port.in.RegisterUserUseCase;
import com.sep.commonModule.domain.model.User;
import com.sep.commonModule.dto.BaseResponse;
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
    // ResponseEntity: represent the entire HTTP response, including status code, headers, and body.
    // BaseReponse: standard response
    // UserDTO: contains the actual data sent to client (body)
    // @Valid: data validation
    // @Request Body: Convert JSON from client ("email":"...", "password":"...") to Java object (RegisterUserRequest request)
    // RegisterUserRequest request: represents data client sent to server ("email":.., "password":..)
    public ResponseEntity<BaseResponse<UserDTO>> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        // Mapping: request -> command
        // Request: contains data from client, is an API DTO
        // Command represent business action (RegisterUserCommand: register, LoginCommand: login,
        // ChangePassword: change password)
        RegisterUserCommand command = new RegisterUserCommand(
                request.getEmail(),
                request.getPassword(),
                request.getFullName()
        );

        // Spring injects an implementation of RegisterUserUseCase (RegisterUserUseCaseImpl)
        // into the constructor of UserController. At runtime, calling registerUserUseCase.execute
        // will actually call RegisterUserUseCaseImpl.execute()
        User registeredUser = registerUserUseCase.execute(command);

        // Mapping: Domain User -> UserDTO
        UserDTO userDTO = UserDTO.builder()
                .userId(registeredUser.getId().value().toString())
                .email(registeredUser.getEmail())
                .fullName(registeredUser.getFullName())
                .status(registeredUser.getStatus())
                .createdAt(registeredUser.getCreatedAt())
                .build();

        URI location = URI.create("/api/users/" + userDTO.getUserId());

        // UserDTO is packed into BaseResponse, then sent back to client
        return ResponseEntity.created(location) // Trả về 201 Created
                .body(BaseResponse.success(userDTO, "User registered successfully"));    }
}

// Controller - UC - Domain - DTO - Response
