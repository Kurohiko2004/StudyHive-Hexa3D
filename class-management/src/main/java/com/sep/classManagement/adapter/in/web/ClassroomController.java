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
import com.sep.classManagement.domain.model.Classroom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<BaseResponse<String>> createClassroom(@RequestBody CreateClassroomRequest request) {
        CreateClassroomCommand command = CreateClassroomCommand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .subjectId(request.getSubjectId())
                .levelId(request.getLevelId())
                .teacherId(request.getTeacherId())
                .schedule(request.getSchedule())
                .build();

        String classId = createClassroomUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(classId, "Tạo lớp học thành công!"));
    }

    @GetMapping
    @Operation(summary = "Lấy danh sách lớp học")
    public ResponseEntity<BaseResponse<PageResponse<ClassroomResponse>>> getClassrooms(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        GetClassroomsQuery query = GetClassroomsQuery.builder().page(page).size(size).build();
        PageResponse<Classroom> domainPage = getClassroomsUseCase.execute(query);

        List<ClassroomResponse> responseList = domainPage.getData().stream()
                .map(domain -> ClassroomResponse.builder()
                        .id(domain.getId())
                        .name(domain.getName())
                        .description(domain.getDescription())
                        .subjectId(domain.getSubjectId())
                        .levelId(domain.getLevelId())
                        .schedule(domain.getSchedule())
                        .build())
                .toList();

        PageResponse<ClassroomResponse> finalResponse = PageResponse.<ClassroomResponse>builder()
                .data(responseList)
                .currentPage(domainPage.getCurrentPage())
                .totalPages(domainPage.getTotalPages())
                .totalElements(domainPage.getTotalElements())
                .hasNext(domainPage.isHasNext())
                .build();

        return ResponseEntity.ok(BaseResponse.success(finalResponse, "Lấy danh sách thành công!"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Xem chi tiết lớp học")
    public ResponseEntity<BaseResponse<ClassroomResponse>> getClassroomById(@PathVariable String id) {
        GetClassroomQuery query = GetClassroomQuery.builder().id(id).build();
        Classroom domain = getClassroomUseCase.execute(query);

        ClassroomResponse response = ClassroomResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .code(domain.getCode())
                .description(domain.getDescription())
                .subjectId(domain.getSubjectId())
                .levelId(domain.getLevelId())
                .schedule(domain.getSchedule())
                .status(String.valueOf(domain.getStatus()))
                .teacherId(domain.getTeacherId())
                .createdAt(domain.getCreatedAt())
                .build();

        return ResponseEntity.ok(BaseResponse.success(response, "Lấy chi tiết lớp học thành công!"));
    }

    @Data
    static class CreateClassroomRequest {
        private String name;
        private String description;
        private String subjectId;
        private String levelId;
        private String teacherId;
        private List<String> schedule;
    }
}
