package com.example.umc7th.domain.openApi.controller;

import com.example.umc7th.domain.openApi.dto.response.OpenApiResDto;
import com.example.umc7th.domain.openApi.service.query.OpenApiQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OpenApiController {

    private final OpenApiQueryService openApiQueryService;

    @GetMapping("/searchStay")
    public CustomResponse<OpenApiResDto.SearchStayResponseListDto> controller(@RequestParam(name = "arrange", defaultValue = "A") String arrange,
                                                                              @RequestParam(name = "page", defaultValue = "1") int page,
                                                                              @RequestParam(name = "offset", defaultValue = "10") int offset) {
        return CustomResponse.onSuccess(openApiQueryService.searchStay(arrange, page, offset));
    }
}
