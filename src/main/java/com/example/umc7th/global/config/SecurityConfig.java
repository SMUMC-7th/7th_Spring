package com.example.umc7th.global.config;

import com.example.umc7th.global.jwt.JwtFilter;
import com.example.umc7th.global.jwt.JwtProvider;
import com.example.umc7th.global.jwt.exception.JwtAccessDeniedHandler;
import com.example.umc7th.global.jwt.exception.JwtAuthenticationEntryPoint;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/member/signup",
            "/member/login",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                // 허용할 URL, 역할별로 나눌 URL, 인증을 요구하는 URL 설정
                .authorizeHttpRequests(request -> request
                        // allowUrl을 모두 허용
                        .requestMatchers(allowUrl).permitAll()
                        // 이외의 요청에 대해서는 인증이 필요하도록 설정
                        .anyRequest().authenticated())
                // jwtFilter를 UsernamePasswordAuthenticationFilter 앞에 오도록 설정
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                // formLogin 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // httpBasic 비활성화
                .httpBasic(HttpBasicConfigurer::disable)
                // OAuth2 Login 설정을 default로 설정
                .oauth2Login(Customizer.withDefaults())
                // csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // 인증 인가에 대한 예외처리
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        // 인가에 대해 예외처리할 Handler 추가
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        // 인증에 대해 예외처리할 Handler 추가
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
        ;

        // build()해서 SecurityFilterChain 형태로 반환
        return http.build();
    }

    @Bean
    // JwtFilter를 Bean에 주입
    public Filter jwtFilter() {
        return new JwtFilter(jwtProvider, userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
