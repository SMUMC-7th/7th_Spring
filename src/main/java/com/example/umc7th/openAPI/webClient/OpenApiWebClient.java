package com.example.umc7th.openAPI.webClient;


import org.springframework.web.reactive.function.client.WebClient;

public interface OpenApiWebClient {
    WebClient getTourWebClient(String language);
//    WebClient getEnglishTourWebClient();

    WebClient getWebClient(String baseUrl);
}
