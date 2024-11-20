package com.example.umc7th.global.jwt.filter;

import com.example.umc7th.global.jwt.JwtProvider;
import com.example.umc7th.global.jwt.error.AuthException;
import com.example.umc7th.global.jwt.error.JwtErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final String AUTH = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String header = request.getHeader(AUTH);
            boolean isValid = checkValidAndDoFilter(header);
            if (!isValid) {
                throw new AuthException(JwtErrorCode.INVALID_TOKEN);
            }
            doFilter(request, response, filterChain);
        } catch (AuthException e) {
            throw e;
        }
    }

    private boolean checkValidAndDoFilter(String header) {
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            String token = header.substring(7);
            return jwtProvider.isValid(token);
        }
        throw new AuthException(JwtErrorCode.JWT_BAD_REQUEST_400);
    }
}
