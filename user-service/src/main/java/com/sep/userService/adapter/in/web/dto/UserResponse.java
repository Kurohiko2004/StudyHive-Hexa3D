package com.sep.userService.adapter.in.web.dto;

import com.sep.commonModule.domain.model.EntityStatus;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Data
@Builder
// UserDTO
public class UserResponse {
    private String id;
    private String email;
    private String fullName;
    private EntityStatus status ;
}
