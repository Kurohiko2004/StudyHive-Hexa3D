package com.sep.learningContents.application.port.out;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionsQuery;
import com.sep.learningContents.domain.model.Question;

import java.util.List;
import java.util.Optional;

public interface LoadQuestionPort {
    PageResponse<Question> loadQuestions(GetQuestionsQuery query);
    Optional<Question> loadQuestionById(String id);
    List<Question> loadQuestionsByGroupId(String groupId);

}
