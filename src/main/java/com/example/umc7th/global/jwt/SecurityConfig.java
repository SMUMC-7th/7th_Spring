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
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        Object AbstractHttpConfigurer;
        http
                // 허용할 URL, 역할별로 나눌 URL, 인증을 요구하는 URL 설정
                .authorizeHttpRequests(request -> request
                        // allowUrl을 모두 허용
                        .requestMatchers(allowUrl).permitAll()
                        // 이외의 요청에 대해서는 인증이 필요하도록 설정
                        .anyRequest().authenticated())
                // jwtFilter를 UsernamePasswordAuthenticationFilter 앞에 오도록 설정
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)

                .formLogin().disable() // formLogin 비활성화
                .httpBasic().disable() // httpBasic 비활성화
                .csrf().disable() // csrf 비활성화

                // formLogin 비활성화
                //.formLogin(AbstractHttpConfigurer::disable)
                // httpBasic 비활성화
                //.httpBasic(HttpBasicConfigurer::disable)
                // csrf 비활성화
                //.csrf(AbstractHttpConfigurer::disable)

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
    public JwtFilter jwtFilter() {
        // JwtFilter를 Bean으로 주입
        return new JwtFilter(jwtProvider, userDetailsService);
    }
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}