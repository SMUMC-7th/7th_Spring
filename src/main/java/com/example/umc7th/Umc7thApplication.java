package com.example.umc7th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // createdAt, updatedAt의 기능을 사용하기 위함(JPA에서 제공)
public class Umc7thApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc7thApplication.class, args);
    }

}
