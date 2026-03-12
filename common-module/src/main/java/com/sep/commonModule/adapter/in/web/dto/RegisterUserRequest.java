package com.sep.commonModule.adapter.in.web.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
    private String fullName;
}
