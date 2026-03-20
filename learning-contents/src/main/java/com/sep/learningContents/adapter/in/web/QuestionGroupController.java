package com.sep.learningContents.adapter.in.web;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupDetailsResponse;
import com.sep.learningContents.adapter.in.web.dto.QuestionGroupResponse;
import com.sep.learningContents.application.port.in.CreateQuestionGroupUseCase;
import com.sep.learningContents.application.port.in.GetQuestionGroupUseCase;
import com.sep.learningContents.application.port.in.GetQuestionGroupsUseCase;
import com.sep.learningContents.application.port.in.command.CreateQuestionGroupCommand;
import com.sep.learningContents.application.port.in.query.GetQuestionGroupQuery;
import com.sep.learningContents.application.port.in.query.GetQuestionGroupsQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question-groups")
@RequiredArgsConstructor
@Tag(name = "Question Group", description = "Question Group management APIs")
public class QuestionGroupController {

    private final CreateQuestionGroupUseCase createQuestionGroupUseCase;
    private final GetQuestionGroupsUseCase getQuestionGroupsUseCase;
    private final GetQuestionGroupUseCase getQuestionGroupUseCase;

    @PostMapping
    @Operation(summary = "Create new question group")
    public ResponseEntity<BaseResponse<String>> createQuestionGroup(@Valid @RequestBody CreateQuestionGroupCommand command) {
        
        command.setAuthorId("TEACHER-MOCK-ID-123");

        String groupId = createQuestionGroupUseCase.createQuestionGroup(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(groupId, "Question group created successfully!"));
    }

    @GetMapping
    @Operation(summary = "Get list of question groups")
    public ResponseEntity<BaseResponse<PageResponse<QuestionGroupResponse>>> getQuestionGroups(
            @ModelAttribute GetQuestionGroupsQuery query) {

        PageResponse<QuestionGroupResponse> response = getQuestionGroupsUseCase.execute(query);

        return ResponseEntity.ok(BaseResponse.success(response, "Get list of question groups successfully!"));
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get question group by id")
    public ResponseEntity<BaseResponse<QuestionGroupDetailsResponse>> getQuestionGroupById(@PathVariable String id) {
        
        GetQuestionGroupQuery query = GetQuestionGroupQuery.builder().id(id).build();
        QuestionGroupDetailsResponse response = getQuestionGroupUseCase.execute(query);

        return ResponseEntity.ok(BaseResponse.success(response, "Get question group by id successfully!"));
    }
}
