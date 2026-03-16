package com.sep.sysAdmin.adapter.out.persistance;

import com.sep.sysAdmin.adapter.out.persistance.SubjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectJpaEntity, String> {
}