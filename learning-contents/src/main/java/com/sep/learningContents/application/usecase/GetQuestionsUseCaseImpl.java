package com.sep.learningContents.application.usecase;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionResponse;
import com.sep.learningContents.application.mapper.QuestionDtoMapper;
import com.sep.learningContents.application.port.in.GetQuestionsUseCase;
import com.sep.learningContents.application.port.in.query.GetQuestionsQuery;
import com.sep.learningContents.application.port.out.LoadQuestionPort;
import com.sep.learningContents.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetQuestionsUseCaseImpl implements GetQuestionsUseCase {
    private final LoadQuestionPort loadQuestionPort;
    private final QuestionDtoMapper mapper;

    @Override
    public PageResponse<QuestionResponse> execute(GetQuestionsQuery query) {
        PageResponse<Question> domainPage = loadQuestionPort.loadQuestions(query);

        return mapper.toPageResponse(domainPage);
    }
}
