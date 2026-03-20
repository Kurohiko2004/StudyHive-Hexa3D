package com.sep.learningContents.application.mapper;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionDetailsResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupDetailsResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupResponse;
import com.sep.learningContents.domain.model.Question;
import com.sep.learningContents.domain.model.QuestionGroup;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionGroupDtoMapper {
    PageResponse<QuestionGroupResponse> toPageResponse(PageResponse<QuestionGroup> domainPage);

    QuestionGroupResponse toResponse(QuestionGroup domain);

    QuestionGroupDetailsResponse toDetailsResponse(QuestionGroup domain);
}
