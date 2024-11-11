package com.example.umc7th.domain.openapi.service.query;

import com.example.umc7th.domain.openapi.dto.OpenApiResponseDTO;
import com.example.umc7th.global.openapi.OpenApiWebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiQueryServiceImpl implements OpenApiQueryService {

    private final OpenApiWebClient openApiWebClient;

    @Value("${openapi.tour.serviceKey}")
    private String serviceKey;

    @Override
    public OpenApiResponseDTO.SearchStayResponseListDTO searchStay(String arrange, int page, int offset) {
        WebClient webClient = openApiWebClient.getTourWebClient("korean");
        Mono<OpenApiResponseDTO.SearchStayResponseListDTO> mono = webClient.get()
                .uri(uri -> uri
                        .path("/searchStay1")
                        .queryParam("numOfRows", offset)
                        .queryParam("pageNo", page)
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "AppTest")
                        .queryParam("_type", "json")
                        .queryParam("arrange", arrange)
                        .queryParam("serviceKey", serviceKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(this::toSearchStayResponseListDTO)
                .doOnError(e -> log.error("Open Api 에러 발생: " + e.getMessage()))
                .doOnSuccess(s -> log.info("관광 정보를 가져오는데 성공했습니다."))
                ;

        return mono.block();
    }

    private OpenApiResponseDTO.SearchStayResponseListDTO toSearchStayResponseListDTO(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<OpenApiResponseDTO.SearchStayResponseDTO> list = new ArrayList<>();
            JsonNode jsonNode = objectMapper.readTree(response).path("response").path("body").path("items").path("item");
            for (JsonNode node : jsonNode) {
                list.add(objectMapper.convertValue(node, OpenApiResponseDTO.SearchStayResponseDTO.class));
            }
            return OpenApiResponseDTO.SearchStayResponseListDTO.from(list);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return OpenApiResponseDTO.SearchStayResponseListDTO.from(null);
    }

}
