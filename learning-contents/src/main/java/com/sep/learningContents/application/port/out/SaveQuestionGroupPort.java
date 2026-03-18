package com.sep.learningContents.application.port.out;

import com.sep.learningContents.domain.model.QuestionGroup;

public interface SaveQuestionGroupPort {
    void save(QuestionGroup questionGroup);
}

