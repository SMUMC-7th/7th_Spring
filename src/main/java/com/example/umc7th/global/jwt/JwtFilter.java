package com.example.umc7th.global.jwt;

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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException{
        //1. HttpServletRequest에 있는 header에서 Authorization header를 가져와 토큰을 가져온다.
        //1-1. header에서 Authorization를 가져오면 ‘Bearer {token}’ 형식으로 담겨있습니다.
        String accessToken = jwtProvider.getAccessTokenFromHeader(request);

        if(accessToken==null || accessToken.isEmpty()){
            filterChain.doFilter(request,response);
            return;
        }

        //2. JwtProvider를 이용하여 토큰에서 이메일을 가져온다.
        String email = jwtProvider.getEmail(accessToken);

        //3. UserDetailService를 이용하여 UserDetail 객체를 가져온다.
        UserDetails details = userDetailsService.loadUserByUsername(email);

        //4. 해당 객체를 SecurityContextHolder에 넣어준다. (PrincipalDetailsService 사용)
        // Filter를 구현하다보면 Authentication 객체를 넣어야될 때가 있어요. 그때 사용하시면 됩니다.
        // Authentication 객체 만들기
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        doFilter(request,response,filterChain);
    }
}
