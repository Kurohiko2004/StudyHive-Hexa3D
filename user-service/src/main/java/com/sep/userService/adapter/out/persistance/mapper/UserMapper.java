package com.sep.userService.adapter.out.persistance.mapper;

import com.sep.userService.adapter.out.persistance.entity.UserJpaEntity;
import com.sep.userService.domain.model.User;
import com.sep.userService.domain.valueobject.HashedPassword;
import com.sep.commonModule.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Tell MapStruct to use UserIdMapper. MapStruct will now use UserIdMapper for 'id'
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", expression = "java(map(domain.getRole()))")
    @Mapping(target = "password", expression = "java(map(domain.getPassword()))")
    UserJpaEntity toJpaEntity(User domain);

    @Mapping(target = "role", expression = "java(map(entity.getRole()))")
    @Mapping(target = "password", expression = "java(mapToHashedPassword(entity.getPassword()))")
    User toDomain(UserJpaEntity entity);

    default String map(Role role) {
        return role == null ? null : role.name();
    }

    default Role map(String role) {
        return role == null ? null : Role.fromOrDefault(role);
    }

    default String map(HashedPassword password) {
        return password == null ? null : password.value();
    }

    default HashedPassword mapToHashedPassword(String password) {
        return password == null ? null : HashedPassword.of(password);
    }
}
