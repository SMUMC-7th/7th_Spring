package com.example.umc7th.openAPI.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.openAPI.dto.OpenApiResponseDTO.SearchStayResponseListDTO;
import com.example.umc7th.openAPI.service.OpenApiQueryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiQueryServiceImpl openApiQueryService;

    @GetMapping("/searchStay")
    public CustomResponse<SearchStayResponseListDTO> controller(
            @RequestParam(name = "arrange", defaultValue = "A") String arrange,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "offset", defaultValue = "10") int offset) {
        return CustomResponse.onSuccess(openApiQueryService.searchStay(arrange, page, offset));
    }
}