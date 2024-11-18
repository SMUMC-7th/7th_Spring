package com.example.umc7th.openApi;


import java.time.Duration;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.example.umc7th.openApi.exception.OpenApiErrorCode;
import com.example.umc7th.openApi.exception.OpenApiException;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.HttpClient;

@Component
@Slf4j
public class OpenApiWebClientImpl implements OpenApiWebClient {

    @Override
    public WebClient getTourWebClient(String language) {
        if (language.equals("korean")) {
            return getWebClient("https://apis.data.go.kr/B551011/KorService1");
        }
        else if (language.equals("english")) {
            return getWebClient("https://apis.data.go.kr/B551011/EngService1");
        } 
        else {
            throw new OpenApiException(OpenApiErrorCode.UNSUPPORTED_LANGUAGE);
        }
    }
    
    // 영문 API를 추가한 경우
    // @Override
    // public WebClient getEnglishTourWebClient() {
    //     return getWebClient("https://apis.data.go.kr/B551011/EngService1");
    // }

    private WebClient getWebClient(String baseUrl) {
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
                log.info("Web Client Request: "+ request.url());
                return next.exchange(request);
            })
            .build();
    }
}