package com.sep.userService.application.port.in;

import com.sep.userService.adapter.in.web.dto.LoginResponse;
import com.sep.userService.domain.model.User;

public interface LoginUseCase {
    /**
     * Executes post-authentication logic: generate token and build login response.
     * The caller (web adapter) is responsible for authenticating the user BEFORE calling this.
     *
     * @param authenticatedUser The domain User that has already been authenticated by Spring Security.
     */
    LoginResponse execute(User authenticatedUser);
}
