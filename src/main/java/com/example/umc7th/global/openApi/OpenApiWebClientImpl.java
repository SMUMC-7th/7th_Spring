package com.example.umc7th.global.openApi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Component
@Slf4j
public class OpenApiWebClientImpl implements OpenApiWebClient {
    @Value("${openapi.baseUrl}")
    private String baseUrl;
    @Override
    public WebClient getKoreanTourWebClient() {
        // 연결 설정
        // TCP 연결 시 응답 시간 초과 값을 설정
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(20000));

        // Uri를 build하는 factory 생성 (baseUrl을 WebClient 대신 여기에 포함하도록)
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        // Uri factory에 인코딩 모드를 NONE으로 바꾸어 인코딩하지 않도록해줍니다.
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        return WebClient.builder()
                .uriBuilderFactory(factory)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter((request, next) -> {
                    log.info("Web Client Request: " + request.url());
                    return next.exchange(request);
                })
                .build();
    }


}
