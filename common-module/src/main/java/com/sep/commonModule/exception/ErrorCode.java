package com.sep.commonModule.exception;

public enum ErrorCode {
    VALIDATION_ERROR("VALIDATION_ERROR"),
    BUSINESS_VALIDATION_ERROR("BUSINESS_VALIDATION_ERROR"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    BAD_REQUEST("BAD_REQUEST"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    UNAUTHORIZED("UNAUTHORIZED"),
    ACCESS_DENIED("ACCESS_DENIED");

    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

