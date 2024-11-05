package com.example.umc7th.global.config;

import com.example.umc7th.global.jwt.JwtProvider;
import com.example.umc7th.global.jwt.error.handler.JwtAccessDeniedHandler;
import com.example.umc7th.global.jwt.error.handler.JwtAuthenticationEntryPoint;
import com.example.umc7th.global.jwt.filter.JwtFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final BCryptPasswordEncoder encoder;
    private final JwtFilter jwtFilter;
    private final JwtProvider jwtProvider;
    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/index.html", "/sign-up", "/login").permitAll()
                        .anyRequest().permitAll())
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        // 인가에 대해 예외처리할 Handler 추가
                        .accessDeniedHandler(accessDeniedHandler)
                        // 인증에 대해 예외처리할 Handler 추가
                        .authenticationEntryPoint(authenticationEntryPoint))
        ;

        return http.build();

    }

    @Bean
    // JwtFilter를 Bean에 주입
    public Filter jwtFilter() {
        return new JwtFilter(jwtProvider);
    }


    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
