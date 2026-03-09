package com.sep.commonModule.exception;

import lombok.Getter;


@Getter
public class BusinessException extends RuntimeException {
    private final int errorCode;

    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    // Mặc định lỗi nghiệp vụ là 400 (Bad Request)
    public BusinessException(String message) {
        super(message);
        this.errorCode = 400;
    }
}