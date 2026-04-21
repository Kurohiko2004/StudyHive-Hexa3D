package com.sep.commonModule.exception;

import lombok.Getter;

@Getter
public class BusinessValidationException extends RuntimeException {
    private final String fieldName;
    private final ErrorCode errorCode;

    public BusinessValidationException(String fieldName, String message) {
        this(ErrorCode.BUSINESS_VALIDATION_ERROR, fieldName, message);
    }

    public BusinessValidationException(ErrorCode errorCode, String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
        this.errorCode = errorCode;
    }
}