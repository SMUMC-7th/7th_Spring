package com.example.umc7th.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    // Swagger OpenAPI 설정을 정의하는 메서드
    public OpenAPI swagger() {
        // Swagger 문서의 제목, 설명, 버전을 설정
        Info info = new Info().title("Umc 7th").description("Umc 7기 실습용 Swagger").version("0.0.1");

        // JWT 토큰 인증 설정을 위한 스키마 이름 정의
        String securityScheme = "JWT TOKEN";
        // SecurityRequirement에 스키마를 추가하여 보안 요구사항을 설정
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityScheme);

        // Components에 보안 스키마 설정을 추가
        // Components는 OpenAPI에서 공통으로 사용되는 구성 요소들을 정의하여 중복을 줄이고 일관성을 제공하는 역할을 함
        // Swagger에서 API 명세의 재사용 가능한 요소를 정의하는 데 사용
        Components components = new Components()
                .addSecuritySchemes(securityScheme, new SecurityScheme()
                        .name(securityScheme) // 보안 스키마의 이름 설정
                        .type(SecurityScheme.Type.HTTP) // HTTP 인증 타입으로 설정
                        .scheme("Bearer") // Bearer 인증 스키마 사용
                        .bearerFormat("JWT")); // Bearer 포맷을 JWT로 설정

        // OpenAPI 인스턴스를 생성하고, 정보, 서버 URL, 보안 요구사항, 컴포넌트를 설정
        return new OpenAPI()
                .info(info) // API 문서 정보 추가
                .addServersItem(new Server().url("/")) // 서버 URL을 "/"로 설정
                .addSecurityItem(securityRequirement) // 보안 요구사항 추가
                .components(components); // 보안 스키마를 포함한 컴포넌트 추가
    }
}