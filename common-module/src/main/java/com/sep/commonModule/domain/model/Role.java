package com.sep.commonModule.domain.model;

public enum Role {
    STUDENT,
    TEACHER,
    CONTENT_MODERATOR,
    CLASS_ADMIN,
    CENTER_MANAGER,
    ADMIN;

    public static Role defaultRole() {
        return STUDENT;
    }

    public static Role fromOrDefault(String rawRole) {
        if (rawRole == null || rawRole.trim().isEmpty()) {
            return defaultRole();
        }

        try {
            return Role.valueOf(rawRole.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid role: " + rawRole);
        }
    }

    public static Role fromRegistrationOrDefault(String rawRole) {
        Role resolvedRole = fromOrDefault(rawRole);
        if (resolvedRole != STUDENT && resolvedRole != TEACHER) {
            throw new IllegalArgumentException("Only STUDENT or TEACHER role is allowed for registration");
        }
        return resolvedRole;
    }
}
