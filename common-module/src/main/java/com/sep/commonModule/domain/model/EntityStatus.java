package com.sep.commonModule.domain.model;

public enum EntityStatus {
    PENDING, // user vừa đăng ký, user phải verify qua email
    ACTIVE,  // user đã verify email
    INACTIVE // user bị khoá acc
}
