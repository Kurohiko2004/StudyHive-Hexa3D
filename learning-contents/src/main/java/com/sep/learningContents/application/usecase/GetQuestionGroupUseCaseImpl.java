package com.sep.learningContents.application.usecase;

import com.sep.commonModule.exception.ResourceNotFoundException;
import com.sep.learningContents.adapter.in.web.dto.QuestionDetailsResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupDetailsResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupItemResponse;
import com.sep.learningContents.application.mapper.QuestionDtoMapper;
import com.sep.learningContents.application.mapper.QuestionGroupDtoMapper;
import com.sep.learningContents.application.port.in.GetQuestionGroupUseCase;
import com.sep.learningContents.application.port.in.query.GetQuestionGroupQuery;
import com.sep.learningContents.application.port.out.LoadQuestionGroupPort;
import com.sep.learningContents.application.port.out.LoadQuestionPort;
import com.sep.learningContents.domain.model.Question;
import com.sep.learningContents.domain.model.QuestionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class GetQuestionGroupUseCaseImpl implements GetQuestionGroupUseCase {

    private final LoadQuestionGroupPort loadQuestionGroupPort;
    private final LoadQuestionPort loadQuestionPort;
    private final QuestionGroupDtoMapper questionGroupMapper;
    private final QuestionDtoMapper questionMapper;

    @Override
    @Transactional(readOnly = true)
    public QuestionGroupDetailsResponse execute(GetQuestionGroupQuery query) {
        QuestionGroup group = loadQuestionGroupPort.loadQuestionGroupById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Question group " + query.getId() + " not found"));

        List<Question> questions = loadQuestionPort.loadQuestionsByGroupId(query.getId());

        QuestionGroupDetailsResponse response = questionGroupMapper.toDetailsResponse(group);

        List<QuestionGroupItemResponse> questionResponses = IntStream.range(0, questions.size())
                .mapToObj(i -> {
                    Question domain = questions.get(i);
                    return QuestionGroupItemResponse.builder()
                            .index(i + 1)
                            .type(domain.getType())
                            .point(domain.getDefaultPoint())
                            .specificConfig(domain.getSpecificConfig())
                            .build();
                })
                .toList();

        response.setQuestions(questionResponses);

        return response;
    }
}
