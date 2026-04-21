package com.sep.userService.adapter.out.persistance;

import com.sep.userService.adapter.out.persistance.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {
    boolean existsByEmail(String email);
    Optional<UserJpaEntity> findByEmail(String email);
}
