package com.example.umc7th.domain.openApi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.util.List;

public class OpenApiResDto {

    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true) // JSON 파싱 시 필드가 없는 경우 무시
    public record SearchStayResponseDto(
            String addr1,
            String title,
            String tel,
            String contentid,
            String contenttypeid,
            String createdtime,
            String firstimage,
            String firstimage2,
            String mapx,
            String mapy
    ) {}

    public record SearchStayResponseListDto(
            List<SearchStayResponseDto> item
    ) {
        public static SearchStayResponseListDto from(List<SearchStayResponseDto> list) {
            return new SearchStayResponseListDto(list);
        }
    }
}
