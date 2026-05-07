package com.sep.userService.application.port.in;

import com.sep.userService.adapter.in.web.dto.UserResponse;
import com.sep.userService.application.command.RegisterUserCommand;

public interface RegisterUserUseCase {
    UserResponse execute(RegisterUserCommand command);
}
