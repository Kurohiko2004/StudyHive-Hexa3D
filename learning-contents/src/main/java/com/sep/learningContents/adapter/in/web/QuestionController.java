package com.sep.learningContents.adapter.in.web;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.learningContents.application.port.in.CreateQuestionUseCase;
import com.sep.learningContents.application.port.in.command.CreateQuestionCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final CreateQuestionUseCase createQuestionUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> createQuestion(@Valid @RequestBody CreateQuestionCommand command) {
        
        command.setAuthorId("TEACHER-MOCK-ID-123");

        String questionId = createQuestionUseCase.createQuestion(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(questionId, "Question created successfully"));
    }
}

