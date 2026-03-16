package com.sep.classManagement.application.port.in;

import com.sep.classManagement.application.port.in.command.CreateCommentCommand;

public interface CreateCommentUseCase {
    String execute(CreateCommentCommand command);
}
