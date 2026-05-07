package com.sep.learningContents.adapter.in.web;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionDetailsResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionResponse;
import com.sep.learningContents.application.port.in.CreateQuestionUseCase;
import com.sep.learningContents.application.port.in.DeleteQuestionUseCase;
import com.sep.learningContents.application.port.in.GetQuestionUseCase;
import com.sep.learningContents.application.port.in.GetQuestionsUseCase;
import com.sep.learningContents.application.port.in.UpdateQuestionUseCase;
import com.sep.learningContents.application.port.in.command.CreateQuestionCommand;
import com.sep.learningContents.application.port.in.command.UpdateQuestionCommand;
import com.sep.learningContents.application.port.in.query.GetQuestionQuery;
import com.sep.learningContents.application.port.in.query.GetQuestionsQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Tag(name = "Learning Content", description = "Question bank management APIs")
public class QuestionController {

    private final CreateQuestionUseCase createQuestionUseCase;
    private final GetQuestionsUseCase getQuestionsUseCase;
    private final GetQuestionUseCase getQuestionUseCase;
    private final UpdateQuestionUseCase updateQuestionUseCase;
    private final DeleteQuestionUseCase deleteQuestionUseCase;

    @PreAuthorize("hasAnyRole('TEACHER', 'CONTENT_MODERATOR')")
    @PostMapping
    @Operation(summary = "Create new question")
    public ResponseEntity<BaseResponse<String>> createQuestion(@Valid @RequestBody CreateQuestionCommand command) {

        String questionId = createQuestionUseCase.createQuestion(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(questionId, "Question created successfully"));
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'CONTENT_MODERATOR')")
    @GetMapping
    @Operation(summary = "Get list of questions")
    public ResponseEntity<BaseResponse<PageResponse<QuestionResponse>>> getQuestions(
            @ModelAttribute GetQuestionsQuery query) {

        PageResponse<QuestionResponse> response = getQuestionsUseCase.execute(query);

        return ResponseEntity.ok(BaseResponse.success(response, "Get list of questions successfully!"));
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'CONTENT_MODERATOR')")
    @GetMapping("/{id}")
    @Operation(summary = "Get question by id")
    public ResponseEntity<BaseResponse<QuestionDetailsResponse>> getQuestionById(@PathVariable String id) {

        GetQuestionQuery query = GetQuestionQuery.builder().id(id).build();
        QuestionDetailsResponse response = getQuestionUseCase.execute(query);

        return ResponseEntity.ok(BaseResponse.success(response, "Get question by id successfully!"));
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'CONTENT_MODERATOR')")
    @PutMapping("/{id}")
    @Operation(summary = "Update question")
    public ResponseEntity<BaseResponse<QuestionDetailsResponse>> updateQuestion(@PathVariable String id,
            @Valid @RequestBody UpdateQuestionCommand command) {
        command.setId(id);
        QuestionDetailsResponse response = updateQuestionUseCase.updateQuestion(command);
        return ResponseEntity.ok(BaseResponse.success(response, "Question updated successfully!"));
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'CONTENT_MODERATOR')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete question")
    public ResponseEntity<BaseResponse<String>> deleteQuestion(@PathVariable String id) {
        String response = deleteQuestionUseCase.deleteQuestion(id);
        return ResponseEntity.ok(BaseResponse.success(response, "Question deleted successfully!"));
    }
}
