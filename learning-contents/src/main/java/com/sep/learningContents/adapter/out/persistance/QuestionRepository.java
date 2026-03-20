package com.sep.learningContents.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionJpaEntity, String>,
        JpaSpecificationExecutor<QuestionJpaEntity> {
    List<QuestionJpaEntity> findByGroupIdOrderByCreatedAtAsc(String groupId);
}
