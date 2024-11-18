package com.example.umc7th.openApi.service;

import com.example.umc7th.openApi.dto.OpenApiResponseDTO;

public interface OpenApiQueryService {
	OpenApiResponseDTO.SearchStayResponseListDTO searchStay(String arrange, int page, int offset);
}
