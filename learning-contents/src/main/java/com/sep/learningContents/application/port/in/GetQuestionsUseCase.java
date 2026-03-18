package com.sep.learningContents.application.port.in;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionsQuery;

public interface GetQuestionsUseCase {

    PageResponse<QuestionResponse> execute(GetQuestionsQuery query);
}
