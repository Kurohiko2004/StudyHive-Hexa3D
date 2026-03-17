package com.sep.learningContents.adapter.out.persistance;

import com.sep.learningContents.application.port.out.SaveQuestionPort;
import com.sep.learningContents.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionPersistenceAdapter implements SaveQuestionPort {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public void save(Question question) {
        QuestionJpaEntity entity = questionMapper.toJpaEntity(question);
        questionRepository.save(entity);
    }
}

