package com.example.umc7th.domain.openapi.service.query;

import com.example.umc7th.domain.openapi.dto.OpenApiResponseDTO;

public interface OpenApiQueryService {
    OpenApiResponseDTO.SearchStayResponseListDTO searchStay(String arrange, int page, int offset);
}
