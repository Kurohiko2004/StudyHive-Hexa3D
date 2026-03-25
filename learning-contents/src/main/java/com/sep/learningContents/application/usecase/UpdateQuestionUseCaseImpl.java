package com.sep.learningContents.application.usecase;

import com.sep.commonModule.exception.ResourceNotFoundException;
import com.sep.learningContents.adapter.in.web.dto.QuestionDetailsResponse;
import com.sep.learningContents.application.mapper.QuestionDtoMapper;
import com.sep.learningContents.application.port.in.UpdateQuestionUseCase;
import com.sep.learningContents.application.port.in.command.UpdateQuestionCommand;
import com.sep.learningContents.application.port.out.CheckUserPort;
import com.sep.learningContents.application.port.out.LoadQuestionPort;
import com.sep.learningContents.application.port.out.SaveQuestionPort;
import com.sep.learningContents.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateQuestionUseCaseImpl implements UpdateQuestionUseCase {

    private final LoadQuestionPort loadQuestionPort;
    private final SaveQuestionPort saveQuestionPort;
    private final QuestionDtoMapper mapper;

    @Override
    public QuestionDetailsResponse updateQuestion(UpdateQuestionCommand command) {
        Question question = loadQuestionPort.loadQuestionById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + command.getId()));

        question.update(
                command.getType(),
                command.getSubjectId(),
                command.getGroupId(),
                command.getSkillId(),
                command.getLevelId(),
                command.getDefaultPoint(),
                command.getQuestionText(),
                command.getAdditionalInstructions(),
                command.getMediaUrl(),
                command.getConfig()
        );

        saveQuestionPort.save(question);
        return mapper.toDetailsResponse(question);
    }
}

