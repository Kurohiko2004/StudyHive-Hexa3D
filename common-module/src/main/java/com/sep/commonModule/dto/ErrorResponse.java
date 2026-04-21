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
@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields in JSON response.
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String errorCode;
    private String error;
    private String message;

    // Field-level validation errors, e.g. {"questionText": "must not be blank"}
    private Map<String, String> fieldErrors;
}