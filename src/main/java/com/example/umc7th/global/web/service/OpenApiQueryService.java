package com.example.umc7th.global.web.service;

import com.example.umc7th.global.web.dto.OpenApiResponseDTO;

public interface OpenApiQueryService {

    OpenApiResponseDTO.SearchStayResponseListDTO searchStay(String arrange, int page, int offset);
}
