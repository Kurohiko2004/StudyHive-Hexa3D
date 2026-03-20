package com.sep.learningContents.application.mapper;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionDetailsResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionResponse;
import com.sep.learningContents.domain.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionDtoMapper {
    PageResponse<QuestionResponse> toPageResponse(PageResponse<Question> domainPage);

    QuestionResponse toResponse(Question domain);
    QuestionDetailsResponse toDetailsResponse(Question domain);
}
