package com.sep.learningContents.application.usecase;

import com.sep.commonModule.exception.ResourceNotFoundException;
import com.sep.learningContents.application.port.in.DeleteQuestionUseCase;
import com.sep.learningContents.application.port.out.DeleteQuestionPort;
import com.sep.learningContents.application.port.out.LoadQuestionPort;
import com.sep.learningContents.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteQuestionUseCaseImpl implements DeleteQuestionUseCase {

    private final LoadQuestionPort loadQuestionPort;
    private final DeleteQuestionPort deleteQuestionPort;

    @Override
    @Transactional
    public String deleteQuestion(String id) {
        Question question = loadQuestionPort.loadQuestionById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));
        deleteQuestionPort.delete(id);
        return question.getId();
    }
}

