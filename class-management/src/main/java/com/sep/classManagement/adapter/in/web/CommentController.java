package com.sep.classManagement.adapter.in.web;

import com.sep.classManagement.application.port.in.CreateCommentUseCase;
import com.sep.classManagement.application.port.in.command.CreateCommentCommand;
import com.sep.commonModule.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class CommentController {
    
    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping("/{postId}/comments")
    @Operation(summary = "Tạo bình luận mới trong bài đăng")
    public ResponseEntity<BaseResponse<String>> createComment(
            @PathVariable String postId,
            @Valid @RequestBody CreateCommentCommand command
    ) {
        command.setPostId(postId);
        command.setAuthorId("TEACHER-MOCK-ID-123");

        String commentId = createCommentUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(commentId, "Comment Success !!"));
    }

}
