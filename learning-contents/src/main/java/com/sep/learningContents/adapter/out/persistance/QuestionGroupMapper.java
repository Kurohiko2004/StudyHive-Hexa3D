package com.sep.learningContents.adapter.out.persistance;

import com.sep.learningContents.domain.model.QuestionGroup;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionGroupMapper {
    QuestionGroupJpaEntity toJpaEntity(QuestionGroup domain);

    QuestionGroup toDomain(QuestionGroupJpaEntity entity);
}

