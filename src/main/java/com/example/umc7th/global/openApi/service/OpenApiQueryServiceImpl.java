package com.example.umc7th.global.openApi.service;

import com.example.umc7th.global.openApi.OpenApiWebClient;
import com.example.umc7th.global.openApi.dto.OpenApiResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiQueryServiceImpl implements OpenApiQueryService {

    // WebClient를 가져오기 위한 빈 주입
    private final OpenApiWebClient openApiWebClient;

    @Value("${openapi.tour.serviceKey}")
    private String serviceKey; // 인증 키

    @Override
    public OpenApiResponseDTO.SearchStayResponseListDTO searchStay(String arrange, int page, int offset) {
        // Web Client 가져오기
        WebClient webClient = openApiWebClient.getKoreanTourWebClient();
        Mono<OpenApiResponseDTO.SearchStayResponseListDTO> mono = webClient.get() // get method 사용
                // UriBuilder를 이용하여 Endpoint와 Query Param 설정
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
                // 응답을 가져오기 위한 method (.onStatus()를 이용해서 Http 상태코드에 따라 다르게 처리해줄 수 있음)
                .retrieve()
                // 응답에서 body만 String 타입으로 가져오기 (ResponseEntity<Object> 중 Object만 String 형식으로 가져오기)
                .bodyToMono(String.class)
                // String 값을 메소드로 매핑하여 OpenApiResponseDTO.SearchStayResponseListDTO로 변경하기
                .map(this::toSearchStayResponseListDTO)
                // 에러가 발생한 경우 log를 찍도록
                .doOnError(e -> log.error("Open Api 에러 발생: " + e.getMessage()))
                // 성공한 경우에도 log를 찍도록
                .doOnSuccess(s -> log.info("관광 정보를 가져오는데 성공했습니다."))
                ;

        // block()을 사용해서 응답을 바로 가져오도록
        return mono.block();
    }

    private OpenApiResponseDTO.SearchStayResponseListDTO toSearchStayResponseListDTO(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // item으로 담을 list 선언
            List<OpenApiResponseDTO.SearchStayResponseDTO> list = new ArrayList<>();
            // JsonNode 형식으로 응답을 읽고 item이 담긴 배열만 읽고 싶기에 item이 있는 배열까지 들어가기
            JsonNode jsonNode = objectMapper.readTree(response).path("response").path("body").path("items").path("item");
            // item 하나씩 처리
            for (JsonNode node : jsonNode) {
                // item 하나씩 읽어서 OpenApiResponseDTO.SearchStayResponseDTO로 변경해서 List에 추가
                list.add(objectMapper.convertValue(node, OpenApiResponseDTO.SearchStayResponseDTO.class));
            }
            // 응답을 만들어서 반환
            return OpenApiResponseDTO.SearchStayResponseListDTO.from(list);
        } catch (Exception e) {
            // 에러 처리
            e.fillInStackTrace();
        }
        return OpenApiResponseDTO.SearchStayResponseListDTO.from(null);
    }

}