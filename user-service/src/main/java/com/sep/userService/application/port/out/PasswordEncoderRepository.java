package com.sep.userService.application.port.out;

public interface PasswordEncoderRepository {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
