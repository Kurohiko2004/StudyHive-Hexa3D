package com.sep.learningContents.application.port.out;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionsQuery;
import com.sep.learningContents.domain.model.Question;

public interface LoadQuestionPort {
    PageResponse<Question> loadQuestions(GetQuestionsQuery query);

}
