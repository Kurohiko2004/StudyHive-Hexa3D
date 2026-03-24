package com.sep.userService.adapter.out.persistance;

import com.sep.userService.adapter.in.web.dto.UserResponse;
import com.sep.userService.adapter.out.persistance.entity.UserJpaEntity;
import com.sep.userService.adapter.out.persistance.mapper.UserMapper;
import com.sep.userService.application.port.out.UserRepository;
import com.sep.userService.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper mapper;

    @Override
    public UserResponse save(User user) {
        UserJpaEntity entity = mapper.toJpaEntity(user);
        UserJpaEntity savedEntity = userJpaRepository.save(entity);
        User savedUser = mapper.toDomain(savedEntity);

        return UserResponse.builder()
                .id(savedUser.getId()) // use mapstruct with UserIdMapper
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .role(savedUser.getRole() == null ? null : savedUser.getRole().name())
                .status(savedUser.getStatus())
                .build();
    }
    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(String id) {
        return userJpaRepository.findById(id).map(mapper::toDomain);
    }
}
