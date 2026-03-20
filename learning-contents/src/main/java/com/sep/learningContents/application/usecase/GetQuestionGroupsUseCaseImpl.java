package com.sep.learningContents.application.usecase;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupResponse;
import com.sep.learningContents.application.mapper.QuestionGroupDtoMapper;
import com.sep.learningContents.application.port.in.GetQuestionGroupsUseCase;
import com.sep.learningContents.application.port.in.query.GetQuestionGroupsQuery;
import com.sep.learningContents.application.port.out.LoadQuestionGroupPort;
import com.sep.learningContents.domain.model.QuestionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetQuestionGroupsUseCaseImpl implements GetQuestionGroupsUseCase {
    private final QuestionGroupDtoMapper mapper;
    private final LoadQuestionGroupPort loadQuestionGroupPort;

    @Override
    public PageResponse<QuestionGroupResponse> execute(GetQuestionGroupsQuery query) {
        PageResponse<QuestionGroup> domainPage = loadQuestionGroupPort.loadQuestionGroups(query);

        return mapper.toPageResponse(domainPage);
    }
}
