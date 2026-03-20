package com.sep.userService.application.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * A simple, internal data structure to carry data from the adapter to the use case.
 * It has no validation annotations as validation is handled at the web adapter boundary.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterUserCommand {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,30}$",
            message = "Passwords must be between 8 and 30 characters and contain at least one letter and one number")
    private String password;

    @Size(min = 4, max = 30, message = "Full name must be between 4 and 30 characters")
    @NotBlank(message = "Full name must not be empty")
    private String fullName;
}
