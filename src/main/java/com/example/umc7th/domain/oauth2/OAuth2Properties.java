package com.example.umc7th.domain.oauth2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2.client")
@Data
@Primary
public class OAuth2Properties {

    private Registration registration;
    private Provider provider;

    @Data
    public static class Registration {
        private Kakao kakao;

        @Data
        public static class Kakao {
            private String clientId;
            private String redirectUri;
        }
    }

    @Data
    public static class Provider {
        private Kakao kakao;

        @Data
        public static class Kakao {
            private String tokenUri;
            private String userInfoUri;
        }
    }
}