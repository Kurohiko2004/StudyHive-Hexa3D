package com.sep.learningContents.adapter.out.persistance;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class QuestionSpecification {

    public static Specification<QuestionJpaEntity> hasSubjectId(String subjectId) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(subjectId)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("subjectId"), subjectId);
        };
    }

    public static Specification<QuestionJpaEntity> hasGroupId(String groupId) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(groupId)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("groupId"), groupId);
        };
    }

    public static Specification<QuestionJpaEntity> hasLevelId(String levelId) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(levelId)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("levelId"), levelId);
        };
    }

    public static Specification<QuestionJpaEntity> hasSkillId(String skillId) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(skillId)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("skillId"), skillId);
        };
    }

    public static Specification<QuestionJpaEntity> hasType(String type) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(type)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<QuestionJpaEntity> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(keyword)) {
                return null;
            }
            String likePattern = "%" + keyword.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("questionText")), likePattern);
        };
    }

    public static Specification<QuestionJpaEntity> filterBy(
            String subjectId,
            String groupId,
            String levelId,
            String skillId,
            String type,
            String keyword
    ) {
        return Specification.where(hasKeyword(keyword))
                .and(hasSubjectId(subjectId))
                .and(hasGroupId(groupId))
                .and(hasLevelId(levelId))
                .and(hasSkillId(skillId))
                .and(hasType(type));
    }
}
