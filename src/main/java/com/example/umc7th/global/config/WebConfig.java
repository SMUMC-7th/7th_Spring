package com.example.umc7th.global.config;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@Slf4j
public class WebConfig {

    @Bean
    public WebClient webClient() {
        // 연결 설정
        // TCP 연결 시 응답 시간 초과 값을 설정
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(20000));

        // WebClient 생성
        return WebClient.builder()
                // Base URL 설정
                .baseUrl("https://apis.data.go.kr/B551011/KorService1")
                // 만들었던 연결 설정 넣어주기
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                // filter를 사용해서 요청을 보낼 때 로그가 찍히도록
                .filter((request, next) -> {
                    log.info("Web Client Request: " + request.url());
                    return next.exchange(request);
                })
                // build로 객체 생성
                .build();
    }
}
