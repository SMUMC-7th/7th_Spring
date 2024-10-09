package com.example.umc7th.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReplyResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReplyPreviewDto {
        public Long id;
        public String content;
        public Long articleId;
        public LocalDateTime createdAt;
        public LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReplyPreviewListDto {
        public List<ReplyPreviewDto> replies;
    }
}
