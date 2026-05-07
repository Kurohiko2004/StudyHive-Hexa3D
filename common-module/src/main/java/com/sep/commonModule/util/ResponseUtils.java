package com.sep.commonModule.util;

import com.sep.commonModule.dto.BaseResponse;
import com.sep.commonModule.dto.ErrorResponse;
import com.sep.commonModule.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Map;

public class ResponseUtils {

    public static BaseResponse<ErrorResponse> createErrorResponse(
            ErrorCode errorCode,
            HttpStatus status,
            String errorType,
            String message,
            Map<String, String> fieldErrors) {

        ErrorResponse errorDetails = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errorCode(errorCode.value())
                .error(errorType)
                .message(message)
                .fieldErrors(fieldErrors)
                .build();

        BaseResponse<ErrorResponse> baseResponse = BaseResponse.error(status.value(), message);
        baseResponse.setData(errorDetails);

        return baseResponse;
    }

    public static ResponseEntity<BaseResponse<ErrorResponse>> buildErrorResponseEntity(
            ErrorCode errorCode,
            HttpStatus status,
            String errorType,
            String message,
            Map<String, String> fieldErrors) {
        
        return new ResponseEntity<>(createErrorResponse(errorCode, status, errorType, message, fieldErrors), status);
    }
}
