package com.sep.userService.adapter.in.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer";

    private String userId;
    private String email;
    private String fullName;
    private String role;

    private String refreshToken;
}