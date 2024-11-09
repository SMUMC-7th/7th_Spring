package com.example.umc7th.global.jwt.filter;

import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.principal.PrincipalDetailsService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
// 매 요청마다 한 번만 실행되는 필터 클래스 정의
// JWT 토큰을 검증하여 인증 정보를 설정하고, 예외 발생 시 커스텀 에러 응답을 반환하는 필터 클래스
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final PrincipalDetailsService principalDetailsService;


    // JWT 검증 및 인증 로직 구현 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // "Authorization" 헤더에서 토큰 가져옴
            String header = request.getHeader("Authorization");

            // 헤더가 null이 아니고 "Bearer "로 시작하는지 확인
            if (header != null && header.startsWith("Bearer ")) {
                // "Bearer " 이후의 토큰 부분만 추출
                String token = header.split(" ")[1];
                // JWT 토큰에서 사용자 이메일을 추출
                String email = jwtProvider.getEmail(token);
                // 이메일로 사용자 정보 조회
                UserDetails userDetails = principalDetailsService.loadUserByUsername(email);

                // 사용자 정보가 있으면 인증 객체를 생성하고, SecurityContext에 저장
                if (userDetails != null) {
                    // 인증 정보 authentication에 담고 SecurityContext에 저장
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // 사용자 정보가 없을 경우 예외 발생
                    throw new MemberException(MemberErrorCode.NOT_FOUND);
                }
            }
            // 다음 필터로 요청을 전달
            filterChain.doFilter(request, response);
        } catch (GeneralException e) { // GeneralException 발생 시 에러 응답 처리
            // 예외의 에러 코드 가져옴
            BaseErrorCode code = e.getCode();
            // 응답 상태를 에러 코드의 상태로 설정
            response.setStatus(code.getStatus().value());
            // 응답 타입을 JSON으로 설정
            response.setContentType("application/json; charset=UTF-8");

            // 실패 응답을 생성하여 CustomResponse 객체로 설정
            CustomResponse<Object> customResponse = CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false, "");

            // JSON 변환을 위한 ObjectMapper 객체 생성
            ObjectMapper om = new ObjectMapper();
            // 응답 출력 스트림에 customResponse 객체를 JSON 형태로 작성
            om.writeValue(response.getOutputStream(), customResponse);

        }
    }
}