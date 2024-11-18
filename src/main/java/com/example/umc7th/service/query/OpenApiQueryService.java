package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.OpenApiResponseDto;

public interface OpenApiQueryService {
    OpenApiResponseDto.SearchStayResponseListDto searchStay(String arrange, int page, int offset);
}