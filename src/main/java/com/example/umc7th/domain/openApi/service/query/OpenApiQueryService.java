package com.example.umc7th.domain.openApi.service.query;

import com.example.umc7th.domain.openApi.dto.response.OpenApiResDto;

public interface OpenApiQueryService {
    OpenApiResDto.SearchStayResponseListDto searchStay(String arrange, int page, int offset);
}
