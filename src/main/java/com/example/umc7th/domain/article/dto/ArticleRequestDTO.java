package com.example.umc7th.domain.article.dto;

import com.example.umc7th.domain.article.entity.Article;
import lombok.*;

// client가 서버로 데이터를 보낼 때 사용하는 DTO
// client는 title과 content를 서버로 보내고, 서버는 이를 기반으로 새로운 Article Entity 생성
public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        private String title;
        private String content;

        public Article toEntity() {
            return Article.builder()
                    .title(this.title)
                    .content(this.getContent())
                    .likeNum(0)
                    .build();
        }
    }

    @Getter
    public static class UpdateArticleDTO {
        private String title;
        private String content;

    }
}