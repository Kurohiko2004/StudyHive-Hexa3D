package com.sep.userService.domain.model;

import com.sep.commonModule.domain.model.EntityStatus;
import com.sep.userService.domain.valueobject.HashedPassword;
import com.sep.commonModule.domain.model.Role;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String id;
    private String email;
    private HashedPassword password;
    private String fullName;
    private Role role;
    private EntityStatus status;
    private Instant createdAt;

//    2. Factory method
    public static User createNew(String email, HashedPassword password, String fullName, Role role) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .password(password)
                .fullName(fullName)
                .role(role == null ? Role.defaultRole() : role)
                .status(EntityStatus.ACTIVE)  // mặc định là pending, sau khi use verify qua email thì thành active
                .createdAt(Instant.now())
                .build();
    }

    // vì đang DDD nên không được validate = JakartaBean
    public void validate() {
        validateEmail();
        validatePassword();
        validateFullName();
        validateRole();
    }

    public void validateEmail() {
        if (this.email == null || this.email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        }


        // email format already validated in Adapter/In (RegisterUseCommand).
        // Just ensure it's not null/empty
//        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//        if (!this.email.matches(emailRegex)) {
//            throw new IllegalArgumentException("Invalidate email format");
//        }
    }

    public void validatePassword() {
        // This validates the Encrypted Password (Hash), so we only check for existence.
        // Complexity validation (length, chars) must be done on the Raw Password BEFORE encryption.
        if (this.password == null || this.password.value() == null || this.password.value().trim().isEmpty()) {
            throw new IllegalArgumentException("Passwords must not be empty");
        }
    }

    public void validateFullName() {
        if (this.fullName == null || this.fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Fullname must not be empty");
        }

        // same as email format validation
//        if (this.fullName.length() < 4 || this.fullName.length() > 30) {
//            throw new IllegalArgumentException("Fullname must be between 4 and 30 characters");
//        }
    }

    public void validateRole() {
        if (this.role == null) {
            throw new IllegalArgumentException("Role must not be null");
        }
    }
}
