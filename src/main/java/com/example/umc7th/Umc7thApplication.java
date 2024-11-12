package com.example.umc7th;

import com.example.umc7th.domain.oauth2.OAuth2Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(OAuth2Properties.class)
public class Umc7thApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc7thApplication.class, args);
    }

}