package com.example.umc7th.global.openApi;

import org.springframework.web.reactive.function.client.WebClient;

public interface OpenApiWebClient {
    // 한국 관광정보를 가져올 수 있는 WebClient를 반환하는 메소드 정의
    WebClient getTourWebClient(String language);
}

