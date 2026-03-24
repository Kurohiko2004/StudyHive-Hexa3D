package com.sep.userService.adapter.in.web.dto;

import com.sep.commonModule.domain.model.EntityStatus;
import lombok.*;

@Data
@Builder
// UserDTO
public class UserResponse {
    private String id;
    private String email;
    private String fullName;
    private String role;
    private EntityStatus status ;
}
