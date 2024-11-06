package com.example.umc7th.global.jwt.exception;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // AccessDeniedHandler와 HttpStatus만 다르고 동일하기 때문에 설명은 생략하겠습니다.

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(401);

        CustomResponse<Object> errorResponse = CustomResponse.onFailure(
                GeneralErrorCode.UNAUTHORIZED_401.getStatus(),
                GeneralErrorCode.UNAUTHORIZED_401.getCode(),
                GeneralErrorCode.UNAUTHORIZED_401.getMessage(),
                false,
                null
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
