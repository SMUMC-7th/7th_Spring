package com.example.umc7th.global.jwt.filter;

import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 1. HttpServletRequest에 있는 header에서 Authorization header를 가져와 토큰을 가져온다.
            String accessToken = jwtProvider.getAccessTokenFromHeader(request);

            if (accessToken == null || accessToken.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            // 2. JwtProvider를 이용하여 토큰에서 이메일을 가져온다.
            String email = jwtProvider.getEmail(accessToken);

            // 3. UserDetailService를 이용하여 UserDetail 객체를 가져온다.
            UserDetails details = userDetailsService.loadUserByUsername(email);
            if (details == null) {
                throw new MemberException(MemberErrorCode.NOT_FOUND);
            }

            // 4. 해당 객체를 SecurityContextHolder에 넣어준다
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            BaseErrorCode code = e.getCode();
            response.setStatus(code.getStatus().value());
            response.setContentType("application/json; charset=UTF-8");

            CustomResponse<Object> customResponse = CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage());

            ObjectMapper om = new ObjectMapper();
            om.writeValue(response.getOutputStream(), customResponse);

        }
    }
}
