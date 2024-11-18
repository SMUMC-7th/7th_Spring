package com.example.umc7th.controller;

import com.example.umc7th.dto.response.OpenApiResponseDto;
import com.example.umc7th.global.apipayload.CustomResponse;
import com.example.umc7th.service.query.OpenApiQueryService;
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
    public CustomResponse<OpenApiResponseDto.SearchStayResponseListDto> controller(@RequestParam(name = "arrange", defaultValue = "A") String arrange,
                                                                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                                                                   @RequestParam(name = "offset", defaultValue = "10") int offset) {
        return CustomResponse.onSuccess(openApiQueryService.searchStay(arrange, page, offset));
    }
}
