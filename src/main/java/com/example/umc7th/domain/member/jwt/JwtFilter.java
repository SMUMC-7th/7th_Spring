package com.example.umc7th.domain.member.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final PrincipalDetailsService principalDetailsService;

    public JwtFilter(JwtProvider jwtProvider, PrincipalDetailsService principalDetailsService) {
        this.jwtProvider = jwtProvider;
        this.principalDetailsService = principalDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. HttpServletRequest에서 Authorization 헤더를 가져옵니다.
        String authorizationHeader = request.getHeader("Authorization");

        // 2. "Bearer {token}" 형식이 맞는지 확인하고 토큰 부분만 추출합니다.
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            // 3. JwtProvider를 이용하여 토큰에서 이메일을 가져옵니다.
            if (jwtProvider.isValid(token)) {
                String email = jwtProvider.getEmail(token);

                // 4. UserDetailsService를 이용하여 UserDetails 객체를 가져옵니다.
                UserDetails userDetails = principalDetailsService.loadUserByUsername(email);

                // 5. Authentication 객체를 생성하고 SecurityContextHolder에 설정합니다.
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 다음 필터로 요청을 전달합니다.
        filterChain.doFilter(request, response);
    }
}
