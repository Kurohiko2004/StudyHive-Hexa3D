package com.sep.classManagement.application.port.in;

import com.sep.classManagement.adapter.in.web.dto.ClassroomDetailsResponse;
import com.sep.classManagement.application.port.in.command.UpdateClassroomCommand;

public interface UpdateClassroomUseCase {
    ClassroomDetailsResponse execute(UpdateClassroomCommand command);
}

