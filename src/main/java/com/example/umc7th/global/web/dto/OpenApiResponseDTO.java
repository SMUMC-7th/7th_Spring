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
    // 해당 어노테이션으로 json 값을 Parsing할 때 필드가 없는 경우 무시하여 에러가 터지는 것을 방지, 한번 없이 돌려보시면 이해가 더 잘 되실겁니다.
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchStayResponseDTO {
        // 아래 변수는 Api 명세서의 응답을 보고 그대로 받고 싶은 값들만 똑같은 이름으로 만들어줍니다.
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
