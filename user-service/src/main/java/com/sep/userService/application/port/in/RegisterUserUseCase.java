package com.sep.userService.application.port.in;


import com.sep.userService.adapter.in.web.dto.UserResponse;
import com.sep.userService.application.command.RegisterUserCommand;
import com.sep.userService.domain.model.User;

public interface RegisterUserUseCase {
    UserResponse execute(RegisterUserCommand command);
}
