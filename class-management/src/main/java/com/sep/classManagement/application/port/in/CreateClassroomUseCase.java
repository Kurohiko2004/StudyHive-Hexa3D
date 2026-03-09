package com.sep.classManagement.application.port.in;

import com.sep.classManagement.application.port.in.command.CreateClassroomCommand;

public interface CreateClassroomUseCase {
    String execute(CreateClassroomCommand command);
}
