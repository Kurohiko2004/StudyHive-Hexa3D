package com.sep.learningContents.application.port.in;

import com.sep.learningContents.application.port.in.command.CreateQuestionGroupCommand;

public interface CreateQuestionGroupUseCase {
    String createQuestionGroup(CreateQuestionGroupCommand command);
}

