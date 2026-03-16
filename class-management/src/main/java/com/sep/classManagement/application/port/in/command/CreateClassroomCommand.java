package com.sep.classManagement.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CreateClassroomCommand {
    private String name;
    private String description;
    private String subjectId;
    private String levelId;
    private String teacherId;
    private List<String> schedule;
}