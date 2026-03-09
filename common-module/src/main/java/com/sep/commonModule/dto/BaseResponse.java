package com.sep.commonModule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cái hộp tiêu chuẩn: Mọi API của dự án ĐỀU PHẢI trả về class này.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private int code;
    private boolean success;
    private String message;
    private T data;

    // Helper method: Trả về khi Thành công có Data
    public static <T> BaseResponse<T> success(T data, String message) {
        return BaseResponse.<T>builder()
                .code(200)
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    // Helper method: Trả về khi Thất bại (Lỗi)
    public static <T> BaseResponse<T> error(int code, String message) {
        return BaseResponse.<T>builder()
                .code(code)
                .success(false)
                .message(message)
                .data(null)
                .build();
    }
}