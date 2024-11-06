package com.example.umc7th.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Configuration으로 설정
@RequiredArgsConstructor
public class SecurityConfig {

    // 인가에 실패한 경우 실행할 예외 처리 handler
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    // 인증에 실패한 경우 실행할 예외 처리 Handler
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtProvider jwtProvider;

    private final UserDetailsService userDetailsService;


    // 허용할 URL을 배열의 형태로 관리
    private final String[] allowUrl = {
            "/",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/member/register",
            "/member/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/member/register", "/member/login").permitAll() // 회원가입, 로그인 URL 허용
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin(formLogin -> formLogin.disable())  // formLogin 비활성화
                .httpBasic(httpBasic -> httpBasic.disable())  // httpBasic 비활성화
                .csrf(csrf -> csrf.disable()) // csrf 비활성화
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(jwtAccessDeniedHandler) // 인가 실패 시 처리
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)); // 인증 실패 시 처리

        return http.build();
    }

    @Bean
    public JwtFilter jwtFilter() {
        // JwtFilter를 Bean으로 주입
        return new JwtFilter(jwtProvider, userDetailsService);
    }
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}