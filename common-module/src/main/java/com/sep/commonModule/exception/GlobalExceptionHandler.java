package com.sep.commonModule.exception;

import com.sep.commonModule.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Bắt các lỗi nghiệp vụ do mình tự ném ra (BusinessException)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(BusinessException ex) {
        BaseResponse<Void> response = BaseResponse.error(ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(ex.getErrorCode()).body(response);
    }

    // 2. Bắt lỗi mặc định (IllegalArgumentException) mà lúc trước mình viết trong hàm validate()
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        BaseResponse<Void> response = BaseResponse.error(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 3. Bắt toàn bộ các lỗi rác/hệ thống (NullPointer, SQL Error...) để Frontend không thấy code đỏ lòm
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGeneralException(Exception ex) {
        // Tương lai dự án lớn sẽ ghi Log lỗi ra file ở đây
        ex.printStackTrace();
        BaseResponse<Void> response = BaseResponse.error(500, "Lỗi hệ thống nội bộ. Vui lòng liên hệ Admin!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}