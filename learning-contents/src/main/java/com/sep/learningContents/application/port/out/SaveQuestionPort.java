package com.sep.learningContents.application.port.out;

import com.sep.learningContents.domain.model.Question;

public interface SaveQuestionPort {
    void save(Question question);
}

