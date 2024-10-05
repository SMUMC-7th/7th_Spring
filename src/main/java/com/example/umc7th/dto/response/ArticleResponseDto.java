package com.example.umc7th.dto.response;

import com.example.umc7th.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ArticleResponseDto {

    public Long id;

    public String title;

    public String content;

    public int likeNum;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public static ArticleResponseDto from(Article article){
        return ArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreated_at())
                .updatedAt(article.getUpdated_at())
                .build();
    }

}
