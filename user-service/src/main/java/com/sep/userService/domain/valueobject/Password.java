package com.sep.userService.domain.valueobject;

/**
 * A Value Object representing a raw, unencrypted password.
 * It validates the password format upon creation, ensuring that an instance
 * of this class can only hold a valid password.
 */
public record Password(String value) {

    /**
     * Factory method to create a new Password object from a raw string.
     * This is the only way to create an instance, enforcing validation.
     *
     * @param rawPassword The raw password string.
     * @return A new, validated Password object.
     * @throws IllegalArgumentException if the password format is invalid.
     */
    public static Password createNew(String rawPassword) {
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Passwords must not be empty");
        }
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,30}$";
        if (!rawPassword.matches(passwordRegex)) {
            throw new IllegalArgumentException("Passwords must be between 8 and 30 characters and contain at least one letter and one number");
        }

        // After validation, create and return the new Password record instance.
        return new Password(rawPassword);
    }
}
