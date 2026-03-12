package com.sep.commonModule.adapter.out.persistance;

import com.sep.commonModule.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UserId> {
    boolean existsByEmail(String email);
}