package com.sep.learningContents.application.port.in;

import com.sep.learningContents.adapter.in.web.dto.QuestionGroupDetailsResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionGroupQuery;

public interface GetQuestionGroupUseCase {
    QuestionGroupDetailsResponse execute(GetQuestionGroupQuery query);
}
