package com.example.umc7th.global.openApi.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.openApi.dto.OpenApiResponseDTO;
import com.example.umc7th.global.openApi.service.OpenApiQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiQueryService openApiQueryService;

    @GetMapping("/searchStay")
    public CustomResponse<OpenApiResponseDTO.SearchStayResponseListDTO> controller(@RequestParam(name = "arrange", defaultValue = "A") String arrange,
                                                                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                                                                   @RequestParam(name = "offset", defaultValue = "10") int offset) {
        return CustomResponse.onSuccess(openApiQueryService.searchStay(arrange, page, offset));
    }
}
