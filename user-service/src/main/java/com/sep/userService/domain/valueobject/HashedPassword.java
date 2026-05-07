package com.sep.userService.domain.valueobject;

/**
 * A Value Object representing an already-encrypted (hashed) password.
 * Unlike {@link Password}, this does NOT validate complexity rules because
 * the value is a hash string (e.g. BCrypt), not the user's raw input.
 * It only ensures the hash is present and non-empty.
 */
public record HashedPassword(String value) {

    /**
     * Factory method to create a HashedPassword from an encoded string.
     *
     * @param encodedPassword The already-hashed password string.
     * @return A new HashedPassword instance.
     * @throws IllegalArgumentException if the encoded password is null or empty.
     */
    public static HashedPassword of(String encodedPassword) {
        if (encodedPassword == null || encodedPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Hashed password must not be empty");
        }
        return new HashedPassword(encodedPassword);
    }
}
