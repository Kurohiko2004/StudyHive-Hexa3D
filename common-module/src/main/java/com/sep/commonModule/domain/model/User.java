package com.sep.commonModule.domain.model;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;


@Builder
public class User {
    private String id;
    private String email;
    private String password;
    private String fullName;
    private EntityStatus status;
    private Instant createdAt;
//    không biết có cần không nên đang comment lại
//    private String createdBy;
//    private Instant updatedAt;
//    private String updatedBy;

//    2. Factory method
    public static User createNew(String email, String username, String password, String fullName) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .password(password)
                .fullName(fullName)
                .status(EntityStatus.PENDING)  // mặc định là pending, sau khi use verify qua email thì thành active
                .createdAt(Instant.now())
                .build();
    }

    // vì đang DDD nên không được validate = JakartaBean
    public void validate() {
        validateEmail();
        validatePassword();
        validateFullName();
    }

    public void validateEmail() {
        if (this.email == null || this.email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!this.email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalidate email format");
        }

        //TODO: unique email validation
    }


    public void validatePassword() {
        if (this.password == null || this.password.trim().isEmpty()) {
            throw new IllegalArgumentException("Passwords must not be empty");
        }

        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,30}$";
        if (!this.password.matches(passwordRegex)) {
            throw new IllegalArgumentException("Passwords must be between 8 and 30 characters and contain at least one letter and one number");
        }
    }

    public void validateFullName() {
        if (this.fullName == null || this.fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Fullname must not be empty");
        }
        if (this.fullName.length() < 4 || this.fullName.length() > 30) {
            throw new IllegalArgumentException("Fullname must be between 4 and 30 characters");
        }
    }
}
