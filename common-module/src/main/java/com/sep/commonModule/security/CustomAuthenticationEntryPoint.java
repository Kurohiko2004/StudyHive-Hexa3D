package com.sep.commonModule.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sep.commonModule.dto.BaseResponse;
import com.sep.commonModule.dto.ErrorResponse;
import com.sep.commonModule.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import com.sep.commonModule.util.ResponseUtils;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Chạy ở filter layer (trước Controller)
// Dùng khi: thiếu/sai token, token hết hạn (còn gọi là 401 unauthorized), không login.
// Mặc dù GlobalExceptionHandler cũng trả về 401 unauthorized thông qua BadCredentialsException,
// nhưng nó chỉ xử lý lỗi khi request đã vào đến tầng Controller (ví dụ api login).
// Đối với các api khác bị chặn ngay tại Filter, cần class này để trả về format BaseResponse đồng nhất.
@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String message = "Full authentication is required to access this resource";

        BaseResponse<ErrorResponse> baseResponse = ResponseUtils.createErrorResponse(
                ErrorCode.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                message,
                null);

        // Ghi JSON vào response
        response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
    }
}