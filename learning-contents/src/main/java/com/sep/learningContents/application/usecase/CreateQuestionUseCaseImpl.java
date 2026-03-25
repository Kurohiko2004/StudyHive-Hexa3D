package com.sep.learningContents.application.usecase;

import com.sep.commonModule.exception.BusinessValidationException;
import com.sep.learningContents.application.port.in.CreateQuestionUseCase;
import com.sep.learningContents.application.port.in.command.CreateQuestionCommand;
import com.sep.learningContents.application.port.out.CheckUserPort;
import com.sep.learningContents.application.port.out.SaveQuestionPort;
import com.sep.learningContents.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateQuestionUseCaseImpl implements CreateQuestionUseCase {

    private final SaveQuestionPort saveQuestionPort;
    private final CheckUserPort checkUserPort;

    @Override
    public String createQuestion(CreateQuestionCommand command) {
        // Validate author
        if (command.getAuthorId() != null && !command.getAuthorId().isBlank()) {
            CheckUserPort.AuthorInfo authorInfo = checkUserPort.getAuthorInfo(command.getAuthorId());
            if (!"ACTIVE".equalsIgnoreCase(authorInfo.status())) {
                throw new BusinessValidationException("authorId", "Author is not active");
            }
            // Role validation is already done in adapter, but we can double check or rely on adapter
        } else {
             throw new BusinessValidationException("authorId", "Author ID is required");
        }

        Question question = Question.createNew(
                command.getType(),
                command.getSubjectId(),
                command.getGroupId(),
                command.getSkillId(),
                command.getLevelId(),
                command.getDefaultPoint(),
                command.getQuestionText(),
                command.getAdditionalInstructions(),
                command.getMediaUrl(),
                command.getAuthorId(),
                command.getConfig()
        );

        saveQuestionPort.save(question);

        return question.getId();
    }
}
