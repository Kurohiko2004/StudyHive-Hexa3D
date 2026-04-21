package com.sep.commonModule.exception;

import com.sep.commonModule.dto.ErrorResponse;
import com.sep.commonModule.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return buildErrorResponse(ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST, "Validation Failed", "Invalid request payload.", errors);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleBusinessValidationException(BusinessValidationException ex) {
        return buildErrorResponse(ex.getErrorCode(), HttpStatus.BAD_REQUEST, "Business Validation Failed", ex.getMessage(), Map.of(ex.getFieldName(), ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildErrorResponse(ex.getErrorCode(), HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage(), null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleUnexpectedException(Exception ex) {
        String message = "Unexpected error occurred: " + ex.getClass().getSimpleName();
        return buildErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", message, null);
    }

    /**
     * TODO: nên để ở đâu đó khác trong common-module
     * Private helper to unify the response format
     */
    private ResponseEntity<BaseResponse<ErrorResponse>> buildErrorResponse(
            ErrorCode errorCode,
            HttpStatus status,
            String errorType,
            String message,
            Map<String, String> fieldErrors) {

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errorCode(errorCode.value())
                .error(errorType)
                .message(message)
                .fieldErrors(fieldErrors)
                .build();

        BaseResponse<ErrorResponse> baseResponse = BaseResponse.error(status.value(), message);
        baseResponse.setData(response);

        return new ResponseEntity<>(baseResponse, status);
    }
}