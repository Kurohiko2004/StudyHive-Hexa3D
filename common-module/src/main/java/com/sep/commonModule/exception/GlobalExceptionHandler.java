package com.sep.commonModule.exception;

import com.sep.commonModule.dto.ErrorResponse;
import com.sep.commonModule.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.sep.commonModule.util.ResponseUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseUtils.buildErrorResponseEntity(ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST,
                "Validation Failed", "Invalid request payload.", errors);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleBusinessValidationException(
            BusinessValidationException ex) {
        return ResponseUtils.buildErrorResponseEntity(ex.getErrorCode(), HttpStatus.BAD_REQUEST,
                "Business Validation Failed", ex.getMessage(), Map.of(ex.getFieldName(), ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseUtils.buildErrorResponseEntity(ex.getErrorCode(), HttpStatus.NOT_FOUND, "Resource Not Found",
                ex.getMessage(), null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseUtils.buildErrorResponseEntity(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Bad Request",
                ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleUnexpectedException(Exception ex) {
        String message = "Unexpected error occurred: " + ex.getClass().getSimpleName();
        return ResponseUtils.buildErrorResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error", message, null);
    }

    // Auth exception
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleBadCredentialsException(Exception ex) {
        return ResponseUtils.buildErrorResponseEntity(ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED, "Unauthorized",
                ex.getMessage(), null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseUtils.buildErrorResponseEntity(ErrorCode.ACCESS_DENIED, HttpStatus.FORBIDDEN, "Forbidden",
                "You do not have permission to access this resource", null);
    }
}
