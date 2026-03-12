package com.sep.commonModule.application.port.in;

import com.sep.commonModule.application.command.RegisterUserCommand;
import com.sep.commonModule.domain.model.User;

public interface RegisterUserUseCase {
    User execute(RegisterUserCommand command);
}
