package com.sep.learningContents.application.port.in;

import com.sep.learningContents.adapter.in.web.dto.QuestionGroupDetailsResponse;
import com.sep.learningContents.application.port.in.command.UpdateQuestionGroupCommand;

public interface UpdateQuestionGroupUseCase {
    QuestionGroupDetailsResponse updateQuestionGroup(UpdateQuestionGroupCommand command);
}

