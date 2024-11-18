package com.example.umc7th.global.web.client;

import com.example.umc7th.global.apiPayload.exception.OpenApiException;
import org.springframework.web.reactive.function.client.WebClient;

public interface OpenApiWebClient {

    // 한국 관광정보를 가져올 수 있는 WebClient를 반환하는 메소드 정의

    WebClient getTourWebClient(String language) throws OpenApiException;
    // 아래에 메소드를 추가하면서 확장해나갈 수 있겠죠?
}
