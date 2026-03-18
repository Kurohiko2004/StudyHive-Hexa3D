package com.sep.learningContents.adapter.in.web;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.learningContents.application.port.in.CreateQuestionGroupUseCase;
import com.sep.learningContents.application.port.in.command.CreateQuestionGroupCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/question-groups")
@RequiredArgsConstructor
@Tag(name = "Question Group", description = "Question Group management APIs")
public class QuestionGroupController {

    private final CreateQuestionGroupUseCase createQuestionGroupUseCase;

    @PostMapping
    @Operation(summary = "Create new question group")
    public ResponseEntity<BaseResponse<String>> createQuestionGroup(@Valid @RequestBody CreateQuestionGroupCommand command) {
        
        command.setAuthorId("TEACHER-MOCK-ID-123");

        String groupId = createQuestionGroupUseCase.createQuestionGroup(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(groupId, "Question group created successfully!"));
    }
}

