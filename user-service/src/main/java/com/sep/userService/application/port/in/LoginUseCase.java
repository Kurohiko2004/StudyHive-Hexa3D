package com.sep.userService.application.port.in;

import com.sep.userService.adapter.in.web.dto.LoginResponse;
import com.sep.userService.application.command.LoginCommand;

public interface LoginUseCase {
    LoginResponse execute(LoginCommand command);
}
