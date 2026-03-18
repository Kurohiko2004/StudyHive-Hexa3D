package com.sep.learningContents.application.usecase;

import com.sep.learningContents.application.port.in.CreateQuestionUseCase;
import com.sep.learningContents.application.port.in.command.CreateQuestionCommand;
import com.sep.learningContents.application.port.out.SaveQuestionPort;
import com.sep.learningContents.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateQuestionUseCaseImpl implements CreateQuestionUseCase {

    private final SaveQuestionPort saveQuestionPort;

    @Override
    public String createQuestion(CreateQuestionCommand command) {
        Question question = Question.createNew(
                command.getType(),
                command.getSubjectId(),
                command.getSkill(),
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
