package com.sep.learningContents.application.usecase;

import com.sep.commonModule.exception.ResourceNotFoundException;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupDetailsResponse;
import com.sep.learningContents.application.mapper.QuestionGroupDtoMapper;
import com.sep.learningContents.application.port.in.UpdateQuestionGroupUseCase;
import com.sep.learningContents.application.port.in.command.UpdateQuestionGroupCommand;
import com.sep.learningContents.application.port.out.LoadQuestionGroupPort;
import com.sep.learningContents.application.port.out.SaveQuestionGroupPort;
import com.sep.learningContents.domain.model.QuestionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateQuestionGroupUseCaseImpl implements UpdateQuestionGroupUseCase {

    private final LoadQuestionGroupPort loadQuestionGroupPort;
    private final SaveQuestionGroupPort saveQuestionGroupPort;
    private final QuestionGroupDtoMapper mapper;

    @Override
    public QuestionGroupDetailsResponse updateQuestionGroup(UpdateQuestionGroupCommand command) {
        QuestionGroup group = loadQuestionGroupPort.loadQuestionGroupById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Question group not found with id: " + command.getId()));

        group.update(
                command.getTitle(),
                command.getDescription(),
                command.getSubjectId(),
                command.getSkillId(),
                command.getLevelId(),
                command.getInstructions(),
                command.getPassage(),
                command.getMediaUrl()
        );

        saveQuestionGroupPort.save(group);
        return mapper.toDetailsResponse(group);
    }
}

