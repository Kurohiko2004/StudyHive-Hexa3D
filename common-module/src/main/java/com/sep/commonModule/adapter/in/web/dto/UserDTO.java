package com.sep.commonModule.adapter.in.web.dto;

import com.sep.commonModule.domain.model.EntityStatus;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
// UserDTO
public class UserDTO {
    @NotNull
    private String userId;

    @Email @NotNull
    private String email;

    @NotNull
    private String fullName;

    @NotNull
    private EntityStatus status ;

    @NotNull
    private Instant createdAt;

}
