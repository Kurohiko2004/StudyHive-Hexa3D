package com.sep.learningContents.application.port.in;

import com.sep.learningContents.adapter.in.web.dto.QuestionDetailsResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionQuery;

public interface GetQuestionUseCase {
    QuestionDetailsResponse execute(GetQuestionQuery query);
}
