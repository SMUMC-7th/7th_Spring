package com.example.umc7th.global.jwt;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final PrincipalDetailsService principalDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //Header의 Authorization 값이 비어있으면 JWT token 전송 안함 -> 로그인 x
        if (authorizationHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        //Header의 Authorization값이 'Bearer ' 로 시작하지 않으면 잘못된 토큰
        if (!authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //전송받은 값에서 "Bearer " 뒷부분 토큰값 추출
        String token = authorizationHeader.split(" ")[1];

        //전송받은 JWT Token이 만료되었으면 다음 필터 진행(인증 x)
        if (jwtProvider.isValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        //JWT Token에서 이메일 추출
        String email;
        try {
            email = jwtProvider.getEmail(token);
        } catch (AuthException e) {
            filterChain.doFilter(request, response);
            return;
        }

        //PrincipalDetailsService를 이용하여 UserDetail 객체를 가져오기
        UserDetails userDetails = principalDetailsService.loadUserByUsername(email);

        //Authentication 객체 만들어주기
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        //만들어준 Authentication객체를 SecurityContextHolder에 넣어주기
        SecurityContextHolder.getContext().setAuthentication(authentication);



        doFilter(request, response, filterChain);
    }
}
