package com.sep.learningContents.application.port.out;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionGroupsQuery;
import com.sep.learningContents.domain.model.Question;
import com.sep.learningContents.domain.model.QuestionGroup;

import java.util.Optional;

public interface LoadQuestionGroupPort {
    PageResponse<QuestionGroup> loadQuestionGroups(GetQuestionGroupsQuery query);
    Optional<QuestionGroup> loadQuestionGroupById(String id);

}
