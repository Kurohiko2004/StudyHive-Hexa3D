package com.sep.userService.application.port.out;

import com.sep.userService.domain.model.User;

public interface TokenGenerator {
    String generateToken(User user);
}
