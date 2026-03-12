package com.sep.commonModule.application.port.in;

import com.sep.commonModule.application.command.CreateUserCommand;
import com.sep.commonModule.domain.model.User;

public interface CreateUserUseCase {
    User execute(CreateUserCommand command);
}
