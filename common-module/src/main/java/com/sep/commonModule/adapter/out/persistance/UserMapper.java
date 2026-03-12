package com.sep.commonModule.adapter.out.persistance;

import com.sep.commonModule.domain.model.User;
import com.sep.commonModule.domain.model.UserId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public UserJpaEntity toEntity(User domain) {
        if (domain == null) return null;
        return UserJpaEntity.builder()
                .id(domain.getId().value().toString()) // Chuyển UserId (record) sang String
                .email(domain.getEmail())
                .password(domain.getPassword())
                .fullName(domain.getFullName())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .build();
    }

    public User toDomain(UserJpaEntity entity) {
        if (entity == null) return null;
        return User.builder()
                .id(new UserId(UUID.fromString(entity.getId()))) // Chuyển String về UserId
                .email(entity.getEmail())
                .password(entity.getPassword())
                .fullName(entity.getFullName())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}