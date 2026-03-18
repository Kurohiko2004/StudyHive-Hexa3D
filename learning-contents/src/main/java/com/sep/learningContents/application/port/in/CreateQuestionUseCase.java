package com.sep.learningContents.application.port.in;

import com.sep.learningContents.application.port.in.command.CreateQuestionCommand;

public interface CreateQuestionUseCase {
    String createQuestion(CreateQuestionCommand command);
}

