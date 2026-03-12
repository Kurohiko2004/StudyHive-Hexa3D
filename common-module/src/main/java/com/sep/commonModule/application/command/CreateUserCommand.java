package com.sep.commonModule.application.command;

import lombok.*;

// Command Query Responsibility Segregation (CQRS) pattern, often used in DDD.
// Purpose: Encapsulates data needed to create a new user
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CreateUserCommand {
    private String email;
    private String password;
    private String fullName;
}
