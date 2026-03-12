package com.sep.commonModule.exception;

import com.sep.commonModule.dto.ErrorResponse;
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
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed", "Dữ liệu đầu vào không hợp lệ", errors);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ErrorResponse> handleBusinessValidationException(BusinessValidationException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Business Validation Failed", ex.getMessage(), Map.of(ex.getFieldName(), ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), null);
    }

    /**
     * TODO: nên để ở đâu đó khác trong common-module
     * Private helper to unify the response format
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status,
            String errorType,
            String message,
            Map<String, String> fieldErrors) {

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(errorType)
                .message(message)
                .fieldErrors(fieldErrors)
                .build();

        return new ResponseEntity<>(response, status);
    }
}