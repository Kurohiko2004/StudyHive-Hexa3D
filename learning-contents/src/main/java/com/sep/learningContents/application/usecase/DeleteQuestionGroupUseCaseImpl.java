package com.sep.learningContents.application.usecase;

import com.sep.commonModule.exception.ResourceNotFoundException;
import com.sep.learningContents.application.port.in.DeleteQuestionGroupUseCase;
import com.sep.learningContents.application.port.out.DeleteQuestionGroupPort;
import com.sep.learningContents.application.port.out.LoadQuestionGroupPort;
import com.sep.learningContents.domain.model.QuestionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteQuestionGroupUseCaseImpl implements DeleteQuestionGroupUseCase {

    private final LoadQuestionGroupPort loadQuestionGroupPort;
    private final DeleteQuestionGroupPort deleteQuestionGroupPort;

    @Override
    @Transactional
    public String deleteQuestionGroup(String id) {
        QuestionGroup questionGroup= loadQuestionGroupPort.loadQuestionGroupById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question group not found with id: " + id));
        deleteQuestionGroupPort.delete(id);
        return questionGroup.getId();
    }
}

