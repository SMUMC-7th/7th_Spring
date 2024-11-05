package com.example.umc7th.global.jwt.exception;

import com.example.umc7th.global.util.HttpResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        JwtErrorCode errorCode = JwtErrorCode.UNAUTHORIZED;
        HttpResponseUtil.setErrorResponse(response, errorCode.getStatus(), errorCode.getErrorResponse());
    }
}
