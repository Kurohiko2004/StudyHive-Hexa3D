package com.sep.sysAdmin.adapter.out.persistance;

import com.sep.sysAdmin.adapter.out.persistance.LevelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LevelJpaEntity, String> {
}