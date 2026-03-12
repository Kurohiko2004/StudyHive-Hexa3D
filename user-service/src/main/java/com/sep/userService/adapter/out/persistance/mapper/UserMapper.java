package com.sep.userService.adapter.out.persistance.mapper;

import com.sep.userService.adapter.out.persistance.entity.UserJpaEntity;
import com.sep.userService.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Tell MapStruct to use UserIdMapper. MapStruct will now use UserIdMapper for 'id'
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserJpaEntity toJpaEntity(User domain);
    User toDomain(UserJpaEntity entity);
}
