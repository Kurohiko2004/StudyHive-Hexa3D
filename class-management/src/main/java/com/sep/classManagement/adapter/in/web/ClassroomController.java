package com.sep.classManagement.adapter.in.web;

import com.sep.classManagement.adapter.in.web.dto.ClassroomDetailsResponse;
import com.sep.classManagement.adapter.in.web.dto.ClassroomResponse;
import com.sep.classManagement.application.port.in.*;
import com.sep.classManagement.application.port.in.command.CreateClassroomCommand;
import com.sep.classManagement.application.port.in.command.UpdateClassroomCommand;
import com.sep.classManagement.application.port.in.query.GetClassroomQuery;
import com.sep.classManagement.application.port.in.query.GetClassroomsQuery;
import com.sep.commonModule.dto.BaseResponse;
import com.sep.commonModule.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
@Tag(name = "Classroom Management", description = "Classroom management APIs")
public class ClassroomController {

    private final CreateClassroomUseCase createClassroomUseCase;
    private final GetClassroomsUseCase getClassroomsUseCase;
    private final GetClassroomUseCase getClassroomUseCase;
    private final UpdateClassroomUseCase updateClassroomUseCase;
    private final DeleteClassroomUseCase deleteClassroomUseCase;

    @PostMapping
    @PreAuthorize("hasAnyRole('CLASS_ADMIN', 'TEACHER')")
    @Operation(summary = "Create new classroom")
    public ResponseEntity<BaseResponse<String>> createClassroom(@RequestBody CreateClassroomCommand command) {
        String classId = createClassroomUseCase.execute(command);
        URI location = URI.create("/api/users/" + classId);
        return ResponseEntity.created(location).body(BaseResponse.success(classId, "Classroom created successfully!"));
    }

    @Operation(summary = "Get list of classrooms")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'CLASS_ADMIN')")
    public ResponseEntity<BaseResponse<PageResponse<ClassroomResponse>>> getClassrooms(
            @ModelAttribute GetClassroomsQuery query) {
        // long start = System.currentTimeMillis();
        PageResponse<ClassroomResponse> response = getClassroomsUseCase.execute(query);
        // long time = System.currentTimeMillis();
        // System.out.println("Thời gian thực thi getClassrooms API: " + (time - start)
        // + " ms");
        return ResponseEntity.ok(BaseResponse.success(response, "Get list of classrooms successfully!"));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'CLASS_ADMIN')")
    @Operation(summary = "View classroom details")
    public ResponseEntity<BaseResponse<ClassroomDetailsResponse>> getClassroomById(@PathVariable String id) {

        GetClassroomQuery query = GetClassroomQuery.builder().id(id).build();
        ClassroomDetailsResponse response = getClassroomUseCase.execute(query);

        return ResponseEntity.ok(BaseResponse.success(response, "Get classroom details successfully!"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@classroomSecurity.isOwner(#id) or hasRole('CLASS_ADMIN')")
    @Operation(summary = "Update classroom details")
    public ResponseEntity<BaseResponse<ClassroomDetailsResponse>> updateClassroomById(@PathVariable String id,
            @RequestBody UpdateClassroomCommand command) {

        command.setId(id);
        ClassroomDetailsResponse response = updateClassroomUseCase.execute(command);

        return ResponseEntity.ok(BaseResponse.success(response, "Update classroom details successfully!"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@classroomSecurity.isOwner(#id) or hasRole('CLASS_ADMIN')")
    @Operation(summary = "Delete classroom")
    public ResponseEntity<BaseResponse<String>> deleteClassroom(@PathVariable String id) {
        String deletedId = deleteClassroomUseCase.execute(id);
        return ResponseEntity.ok(BaseResponse.success(deletedId, "Delete classroom successfully!"));
    }

}