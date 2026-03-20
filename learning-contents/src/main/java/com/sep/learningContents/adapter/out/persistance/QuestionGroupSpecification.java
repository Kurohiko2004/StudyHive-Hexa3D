package com.sep.learningContents.adapter.out.persistance;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class QuestionGroupSpecification {

    public static Specification<QuestionGroupJpaEntity> hasSubjectId(String subjectId) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(subjectId)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("subjectId"), subjectId);
        };
    }

    public static Specification<QuestionGroupJpaEntity> hasLevelId(String levelId) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(levelId)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("levelId"), levelId);
        };
    }

    public static Specification<QuestionGroupJpaEntity> hasSkillId(String skillId) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(skillId)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("skillId"), skillId);
        };
    }

    public static Specification<QuestionGroupJpaEntity> hasType(String type) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(type)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<QuestionGroupJpaEntity> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(keyword)) {
                return null;
            }
            String likePattern = "%" + keyword.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("questionText")), likePattern);
        };
    }

    public static Specification<QuestionGroupJpaEntity> filterBy(
            String subjectId,
            String levelId,
            String skillId,
            String type,
            String keyword
    ) {
        return Specification.where(hasKeyword(keyword))
                .and(hasSubjectId(subjectId))
                .and(hasLevelId(levelId))
                .and(hasSkillId(skillId))
                .and(hasType(type));
    }
}
