package com.example.umc7th.openAPI.service;

import com.example.umc7th.openAPI.dto.OpenApiResponseDTO;

public interface OpenApiQueryService {
    OpenApiResponseDTO.SearchStayResponseListDTO searchStay(String arrange, int page, int offset);
}
