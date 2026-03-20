package com.sep.commonModule.exception;

import lombok.Getter;

@Getter
public class BusinessValidationException extends RuntimeException {
    private final String fieldName;

    public BusinessValidationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}