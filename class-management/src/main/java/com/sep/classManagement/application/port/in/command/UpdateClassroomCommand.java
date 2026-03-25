package com.sep.classManagement.application.port.in.command;

import com.sep.commonModule.domain.model.EntityStatus;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClassroomCommand {

    private String id;

    @Size(max = 50, message = "Class name must be between 1 and 50 characters")
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private String subjectId;
    private String levelId;
    private String teacherId;
    private List<String> schedule;
    private EntityStatus status;
}

