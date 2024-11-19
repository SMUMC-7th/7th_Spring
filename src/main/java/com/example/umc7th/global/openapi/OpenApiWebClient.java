package com.example.umc7th.global.openapi;

import org.springframework.web.reactive.function.client.WebClient;

public interface OpenApiWebClient {
    WebClient getTourWebClient(String language);
}
