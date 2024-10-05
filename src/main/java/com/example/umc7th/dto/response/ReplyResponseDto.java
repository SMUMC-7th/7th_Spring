package com.example.umc7th.dto.response;


import com.example.umc7th.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReplyResponseDto {

    public Long articleId;
    public String content;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public static ReplyResponseDto from(Reply reply){
        return ReplyResponseDto.builder()
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .createdAt(reply.getCreated_at())
                .updatedAt(reply.getUpdated_at())
                .build();
    }
}
