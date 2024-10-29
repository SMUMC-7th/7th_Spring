package com.example.umc7th.global.jwt.filter;

import com.example.umc7th.domain.member.principal.PrincipalDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final BCryptPasswordEncoder encoder;
    private final PrincipalDetailsService principalDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 구현할 부분
        encoder.encode("String");
        UserDetails details = principalDetailsService.loadUserByUsername("");
        new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
        doFilter(request, response, filterChain);
    }
}
