package com.sep.classManagement.application.port.in;

import com.sep.classManagement.application.port.in.command.CreateClassPostCommand;

public interface CreateClassPostUseCase {
    String execute (CreateClassPostCommand command);
}
