package com.sep.commonModule.domain.model;
import org.springframework.util.Assert;
import java.util.UUID;

public record UserId(UUID value) {
    public static UserId createNew() {
        return new UserId(UUID.randomUUID());
    }
}