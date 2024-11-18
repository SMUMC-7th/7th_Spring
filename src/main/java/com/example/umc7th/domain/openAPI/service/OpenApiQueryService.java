package com.example.umc7th.domain.openAPI.service;


import com.example.umc7th.domain.openAPI.dto.OpenApiResponseDTO;

public interface OpenApiQueryService {
    public OpenApiResponseDTO.SearchStayResponseListDTO searchStay(String arrange, int page, int offset);
}