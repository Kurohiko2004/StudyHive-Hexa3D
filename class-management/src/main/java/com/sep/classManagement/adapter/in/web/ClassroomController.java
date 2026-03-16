package com.sep.classManagement.adapter.in.web;

import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.application.port.in.CreateClassroomUseCase;
import com.sep.classManagement.application.port.in.GetClassroomUseCase;
import com.sep.classManagement.application.port.in.GetClassroomsUseCase;
import com.sep.classManagement.application.port.in.command.CreateClassroomCommand;
import com.sep.classManagement.application.port.in.query.GetClassroomQuery;
import com.sep.classManagement.application.port.in.query.GetClassroomsQuery;
import com.sep.commonModule.dto.BaseResponse;
import com.sep.commonModule.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
@Tag(name = "Classroom Management", description = "Các API quản lý lớp học")
public class ClassroomController {

    private final CreateClassroomUseCase createClassroomUseCase;
    private final GetClassroomsUseCase getClassroomsUseCase;
    private final GetClassroomUseCase getClassroomUseCase;

    @PostMapping
    @Operation(summary = "Tạo lớp học mới")
    public ResponseEntity<BaseResponse<String>> createClassroom(@RequestBody CreateClassroomCommand command) {
        String classId = createClassroomUseCase.execute(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(classId).toUri();
        return ResponseEntity.created(location).body(BaseResponse.success(classId, "Tạo lớp học thành công!"));
    }

    @GetMapping
    @Operation(summary = "Lấy danh sách lớp học")
    public ResponseEntity<BaseResponse<PageResponse<ClassroomResponse>>> getClassrooms(
            @ModelAttribute GetClassroomsQuery query) {

        PageResponse<ClassroomResponse> response = getClassroomsUseCase.execute(query);

        return ResponseEntity.ok(BaseResponse.success(response, "Lấy danh sách thành công!"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Xem chi tiết lớp học")
    public ResponseEntity<BaseResponse<ClassroomResponse>> getClassroomById(@PathVariable String id) {

        GetClassroomQuery query = GetClassroomQuery.builder().id(id).build();
        ClassroomResponse response = getClassroomUseCase.execute(query);

        return ResponseEntity.ok(BaseResponse.success(response, "Lấy chi tiết lớp học thành công!"));
    }
}