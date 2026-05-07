package com.sep.userService.application.port.out;

import com.sep.userService.domain.valueobject.HashedPassword;

public interface PasswordEncoderRepository {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, HashedPassword encodedPassword);
}
