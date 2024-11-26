package com.example.umc7th.global.web.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.web.dto.OpenApiResponseDTO;
import com.example.umc7th.global.web.service.OpenApiQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiQueryService openApiQueryService;

    @GetMapping("/searchStay")
    @Operation(summary = "숙소 정보 조회 API")
    public CustomResponse<OpenApiResponseDTO.SearchStayResponseListDTO> controller(@RequestParam(name = "arrange", defaultValue = "A") String arrange,
                                                                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                                                                   @RequestParam(name = "offset", defaultValue = "10") int offset,
                                                                                   @RequestParam(name = "lang", defaultValue = "korean") String lang) {
        return CustomResponse.onSuccess(openApiQueryService.searchStay(arrange, page, offset, lang));
    }
}