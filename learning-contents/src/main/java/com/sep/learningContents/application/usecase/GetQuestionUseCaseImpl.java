package com.sep.learningContents.application.usecase;

import com.sep.commonModule.exception.ResourceNotFoundException;
import com.sep.learningContents.adapter.in.web.dto.QuestionDetailsResponse;
import com.sep.learningContents.application.mapper.QuestionDtoMapper;
import com.sep.learningContents.application.port.in.GetQuestionUseCase;
import com.sep.learningContents.application.port.in.query.GetQuestionQuery;
import com.sep.learningContents.application.port.out.LoadQuestionPort;
import com.sep.learningContents.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetQuestionUseCaseImpl implements GetQuestionUseCase {

    private final LoadQuestionPort loadQuestionPort;
    private final QuestionDtoMapper mapper;

    @Override
    public QuestionDetailsResponse execute(GetQuestionQuery query) {
        Question domain = loadQuestionPort.loadQuestionById(query.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Question with ID " + query.getId() + " not found"));

        return mapper.toDetailsResponse(domain);
    }
}
