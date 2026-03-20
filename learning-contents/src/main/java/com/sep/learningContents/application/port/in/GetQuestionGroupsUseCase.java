package com.sep.learningContents.application.port.in;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionGroupsQuery;

public interface GetQuestionGroupsUseCase {
    PageResponse<QuestionGroupResponse> execute(GetQuestionGroupsQuery query);
}
