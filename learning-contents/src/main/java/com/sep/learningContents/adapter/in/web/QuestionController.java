package com.sep.learningContents.adapter.in.web;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionResponse;
import com.sep.learningContents.application.port.in.CreateQuestionUseCase;
import com.sep.learningContents.application.port.in.GetQuestionsUseCase;
import com.sep.learningContents.application.port.in.command.CreateQuestionCommand;
import com.sep.learningContents.application.port.in.query.GetQuestionsQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Tag(name = "Learning Content", description = "Question bank management APIs")
public class QuestionController {

    private final CreateQuestionUseCase createQuestionUseCase;
    private final GetQuestionsUseCase getQuestionsUseCase;

    @PostMapping
    @Operation(summary = "Create new question")
    public ResponseEntity<BaseResponse<String>> createQuestion(@Valid @RequestBody CreateQuestionCommand command) {
        
        command.setAuthorId("TEACHER-MOCK-ID-123");

        String questionId = createQuestionUseCase.createQuestion(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(questionId, "Question created successfully"));
    }

    @GetMapping
    @Operation(summary = "Get list of questions")
    public ResponseEntity<BaseResponse<PageResponse<QuestionResponse>>> getQuestions(
            @ModelAttribute GetQuestionsQuery query) {

        PageResponse<QuestionResponse> response = getQuestionsUseCase.execute(query);

        return ResponseEntity.ok(BaseResponse.success(response, "Get list of questions successfully!"));
    }
}

