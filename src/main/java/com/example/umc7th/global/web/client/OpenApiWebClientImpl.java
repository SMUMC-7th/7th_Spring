package com.example.umc7th.global.web.client;

import com.example.umc7th.global.web.exception.OpenApiErrorCode;
import com.example.umc7th.global.web.exception.OpenApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

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

    private WebClient getWebClient(String baseUrl) {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(30000));

        return WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter((request, next) -> {
                    log.info("Web Client Request: "+ request.url());
                    return next.exchange(request);
                })
                .build();
    }
}
