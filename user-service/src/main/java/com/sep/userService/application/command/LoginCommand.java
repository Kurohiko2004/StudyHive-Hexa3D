package com.sep.userService.application.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginCommand {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    private String email;

    @NotBlank(message = "Password must not be empty")
    private String password;
}
