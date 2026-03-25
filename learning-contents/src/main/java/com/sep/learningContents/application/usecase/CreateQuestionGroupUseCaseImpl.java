package com.sep.learningContents.application.usecase;

import com.sep.commonModule.exception.BusinessValidationException;
import com.sep.learningContents.application.port.in.CreateQuestionGroupUseCase;
import com.sep.learningContents.application.port.in.command.CreateQuestionCommand;
import com.sep.learningContents.application.port.in.command.CreateQuestionGroupCommand;
import com.sep.learningContents.application.port.out.CheckUserPort;
import com.sep.learningContents.application.port.out.SaveQuestionGroupPort;
import com.sep.learningContents.application.port.out.SaveQuestionPort;
import com.sep.learningContents.domain.model.Question;
import com.sep.learningContents.domain.model.QuestionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateQuestionGroupUseCaseImpl implements CreateQuestionGroupUseCase {

    private final SaveQuestionGroupPort saveQuestionGroupPort;
    private final SaveQuestionPort saveQuestionPort;
    private final CheckUserPort checkUserPort;

    @Override
    @Transactional
    public String createQuestionGroup(CreateQuestionGroupCommand command) {
        // Validate author
        if (command.getAuthorId() != null && !command.getAuthorId().isBlank()) {
            CheckUserPort.AuthorInfo authorInfo = checkUserPort.getAuthorInfo(command.getAuthorId());
            if (!"ACTIVE".equalsIgnoreCase(authorInfo.status())) {
                throw new BusinessValidationException("authorId", "Author is not active");
            }
        } else {
            throw new BusinessValidationException("authorId", "Author ID is required");
        }

        // 1. Create and save Question Group
        QuestionGroup group = QuestionGroup.createNew(
                command.getTitle(),
                command.getDescription(),
                command.getSubjectId(),
                command.getSkillId(),
                command.getLevelId(),
                command.getInstructions(),
                command.getPassage(),
                command.getMediaUrl(),
                command.getAuthorId()
        );

        saveQuestionGroupPort.save(group);

        // 2. If there are questions, create and save them linked to this group
        if (command.getQuestions() != null && !command.getQuestions().isEmpty()) {
            for (CreateQuestionCommand questionCmd : command.getQuestions()) {
                Question question = Question.createNew(
                        questionCmd.getType(),
                        group.getSubjectId(), // Inherit from group
                        group.getId(),        // Link to group
                        group.getSkillId(),   // Inherit from group
                        group.getLevelId(),   // Inherit from group
                        questionCmd.getDefaultPoint(),
                        questionCmd.getQuestionText(),
                        questionCmd.getAdditionalInstructions(),
                        questionCmd.getMediaUrl(),
                        group.getAuthorId(),
                        questionCmd.getConfig()
                );
                saveQuestionPort.save(question);
            }
        }

        return group.getId();
    }
}
