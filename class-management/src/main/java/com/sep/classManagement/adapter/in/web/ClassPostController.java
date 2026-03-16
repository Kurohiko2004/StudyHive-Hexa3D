package com.sep.classManagement.adapter.in.web;

import com.sep.classManagement.application.port.in.CreateClassPostUseCase;
import com.sep.classManagement.application.port.in.command.CreateClassPostCommand;
import com.sep.commonModule.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
@Tag(name = "Class Post Management", description = "Các API bảng tin/bài đăng của Lớp học")
public class ClassPostController {

    private final CreateClassPostUseCase createClassPostUseCase;

    @PostMapping(value = "/{classroomId}/posts")
    @Operation(summary = "Tạo bài đăng mới trong lớp")
    public ResponseEntity<BaseResponse<String>> createPost(
            @PathVariable("classroomId") String classroomId,
            @Valid @RequestBody CreateClassPostCommand command) {

        command.setClassroomId(classroomId);
        command.setAuthorId("TEACHER-MOCK-ID-123");

        String postId = createClassPostUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(postId, "Đăng bài thành công!"));
    }
}