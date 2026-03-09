package com.sep.classManagement.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateClassroomCommand {
    private String name;
    private String description;
    private String teacherId;

    public static interface CreateClassroomUseCase {
        String execute(CreateClassroomCommand command);
    }
}