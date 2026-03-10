package com.sep.classManagement.adapter.in.web;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.classManagement.application.port.in.CreateClassroomUseCase;
import com.sep.classManagement.application.port.in.command.CreateClassroomCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Classroom Management", description = "Các API quản lý lớp học")
@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final CreateClassroomUseCase createClassroomUseCase;

    @Operation(summary = "Tạo lớp học mới", description = "Giáo viên tạo lớp học mới để quản lý học sinh và bài tập.")
    @PostMapping
    public ResponseEntity<BaseResponse<String>> createClassroom(@RequestBody CreateClassroomRequest request) {
        // Tạm mock ID giáo viên
        String currentTeacherId = "TEACHER-MOCK-ID-123";

        CreateClassroomCommand command = new CreateClassroomCommand(
                request.getName(),
                request.getDescription(),
                currentTeacherId
        );

        String classId = createClassroomUseCase.execute(command);

        // Trả về BaseResponse chuẩn
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(classId, "Tạo lớp học thành công!"));
    }

    @Data
    static class CreateClassroomRequest {
        private String name;
        private String description;
    }
}