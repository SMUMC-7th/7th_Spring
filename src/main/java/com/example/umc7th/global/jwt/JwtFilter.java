package com.example.umc7th.global.jwt;

import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", authorizationHeader);

        log.info("Authorization header로부터 토큰을 분리합니다.");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.split(" ")[1];

        // 토큰 valid 검사
        if (!jwtProvider.isValid(token)) {

            log.info("토큰 valid 검증 실패");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("JwtProvider를 이용하여 토큰에서 이메일을 가져옵니다.");
        String email = jwtProvider.getEmail(token);

        // UserDetailService 이용하여 UserDetail 객체 가져오기
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // 가져온 객체를 SecurityContextHolder 에 넣어주기
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("인증완료 객체 SecurityContextHolder에 저장");

        filterChain.doFilter(request, response);
    }
}
