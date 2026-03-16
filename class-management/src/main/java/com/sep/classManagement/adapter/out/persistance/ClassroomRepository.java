package com.sep.classManagement.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; // <-- Import cái này
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends
        JpaRepository<ClassroomJpaEntity, String>,
        JpaSpecificationExecutor<ClassroomJpaEntity> {
}