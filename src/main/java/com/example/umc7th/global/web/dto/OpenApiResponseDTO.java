package com.example.umc7th.global.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class OpenApiResponseDTO {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    // 해당 어노테이션으로 json 값을 Parsing할 때 필드가 없는 경우 무시하여 에러가 터지는 것을 방지
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchStayResponseDTO {
        private String addr1;
        private String title;
        private String tel;
        private String contentid;
        private String contenttypeid;
        private String createdtime;
        private String firstimage;
        private String firstimage2;
        private String mapx;
        private String mapy;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class SearchStayResponseListDTO {
        private List<SearchStayResponseDTO> item;

        public static SearchStayResponseListDTO from(List<SearchStayResponseDTO> list) {
            return SearchStayResponseListDTO.builder()
                    .item(list)
                    .build();
        }
    }
}
