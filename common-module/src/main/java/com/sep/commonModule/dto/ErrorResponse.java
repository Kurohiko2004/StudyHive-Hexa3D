package com.sep.commonModule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Chỉ render các trường không null
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;

    // Nơi chứa chi tiết lỗi của từng field (VD: {"questionText": "không được để trống"})
    private Map<String, String> fieldErrors;
}