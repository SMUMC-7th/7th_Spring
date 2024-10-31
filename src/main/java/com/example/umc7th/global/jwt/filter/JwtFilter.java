package com.example.umc7th.global.jwt.filter;

import com.example.umc7th.entity.Member;
import com.example.umc7th.global.apipayload.code.MemberErrorCode;
import com.example.umc7th.global.apipayload.exception.MemberException;
import com.example.umc7th.global.jwt.userdetails.PrincipalDetails;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.example.umc7th.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // 해더에서 토큰 추출
            String tokenFromHeader = request.getHeader("Authorization");
            if (tokenFromHeader == null || !tokenFromHeader.startsWith("Bearer ")) {
                return;
            }
            // 1. 해더에서 토큰 분리
            String token = tokenFromHeader.split(" ")[1];

            // 2. JwtProvider를 이용하여 토큰에서 이메일을 가져오기
            String email = jwtProvider.getEmail(token);

            // 3. UserDetailService를 이용하여 UserDetail 객체를 가져오기
            Member member = memberRepository.findByEmail(email).orElseThrow(
                    () -> new MemberException(MemberErrorCode.NOT_FOUND));

            PrincipalDetails principalDetails = new PrincipalDetails(member);


            // Authentication 객체 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails,
                    null,
                    principalDetails.getAuthorities());

            // 4. SecurityContextHolder 에 현재 인증 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            doFilter(request, response, filterChain);
        }
        catch (ExpiredJwtException e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 401 return
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Access Token 이 만료되었습니다.");
        }


    }

}