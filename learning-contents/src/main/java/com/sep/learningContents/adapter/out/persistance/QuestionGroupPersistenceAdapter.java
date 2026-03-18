package com.sep.learningContents.adapter.out.persistance;

import com.sep.learningContents.application.port.out.SaveQuestionGroupPort;
import com.sep.learningContents.domain.model.QuestionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionGroupPersistenceAdapter implements SaveQuestionGroupPort {

    private final QuestionGroupRepository questionGroupRepository;
    private final QuestionGroupMapper questionGroupMapper;

    @Override
    public void save(QuestionGroup questionGroup) {
        QuestionGroupJpaEntity entity = questionGroupMapper.toJpaEntity(questionGroup);
        questionGroupRepository.save(entity);
    }
}

