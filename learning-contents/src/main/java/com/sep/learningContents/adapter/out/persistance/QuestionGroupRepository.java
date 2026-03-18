package com.sep.learningContents.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionGroupRepository extends JpaRepository<QuestionGroupJpaEntity, String>,
        JpaSpecificationExecutor<QuestionGroupJpaEntity> {
}

