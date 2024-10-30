package com.example.umc7th.domain.article.dto;

import com.example.umc7th.domain.article.entity.Article;
import lombok.Getter;

// client가 서버로 데이터를 보낼 때 사용하는 DTO
// client는 title과 content를 서버로 보내고, 서버는 이를 기반으로 새로운 Article Entity 생성
public class ArticleRequestDTO {
    @Getter
    public static class CreateArticleDTO {

        private String title;
        private String content;

        /**
         * DTO를 Article 엔티티로 변환하는 메서드
         * 변환 이유: 데이터베이스와 직접적으로 관련된 작업은 DTO가 아닌 엔티티 객체가 처리
         *
         * @return Article 엔티티 객체
         */
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

        /**
         * DTO를 Article로 변환하는 메서드
         *
         * @return Article 엔티티 객체
         */
        public Article toEntity() {
            return Article.builder()
                    .title(this.title)
                    .content(this.content)
                    .build();
        }
    }
}