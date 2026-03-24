package com.sep.userService.adapter.out.persistance.mapper;

import com.sep.userService.adapter.out.persistance.entity.UserJpaEntity;
import com.sep.userService.domain.model.User;
import com.sep.userService.domain.valueobject.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Tell MapStruct to use UserIdMapper. MapStruct will now use UserIdMapper for 'id'
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", expression = "java(map(domain.getRole()))")
    UserJpaEntity toJpaEntity(User domain);

    @Mapping(target = "role", expression = "java(map(entity.getRole()))")
    User toDomain(UserJpaEntity entity);

    default String map(Role role) {
        return role == null ? null : role.name();
    }

    default Role map(String role) {
        return role == null ? null : Role.fromOrDefault(role);
    }
}
