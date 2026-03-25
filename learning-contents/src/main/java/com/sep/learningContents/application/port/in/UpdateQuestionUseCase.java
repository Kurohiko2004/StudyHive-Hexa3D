package com.sep.learningContents.application.port.in;

import com.sep.learningContents.adapter.in.web.dto.QuestionDetailsResponse;
import com.sep.learningContents.application.port.in.command.UpdateQuestionCommand;

public interface UpdateQuestionUseCase {
    QuestionDetailsResponse updateQuestion(UpdateQuestionCommand command);
}

