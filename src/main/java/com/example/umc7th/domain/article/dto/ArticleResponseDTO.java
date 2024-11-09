package com.example.umc7th.domain.article.dto;

import com.example.umc7th.domain.article.entity.Article;
import lombok.*;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 서버가 클라이언트로 데이터 반환할 때 사용하는 DTO
// 서버에서는 데이터를 가공해서 클라이언트에게 필요로 하는 정보만 보내줌
// 중첩 클래스로 구성. 내부 클래스들은 static으로 설정하여 인스턴스가 필요 없이 독립적으로 사용할 수 있도록 함
public class ArticleResponseDTO {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    // 클라이언트가 생성된 객체의 ID나 생성 시간을 확인하고, 이를 통해 추가적인 작업을 수행할 수 있도록 ResponseDTO 보내줌
    /** 생성된 게시물에 대한 ResponseDTO */
    public static class CreateArticleResponseDTO {
        private Long id;
        private LocalDateTime createdAt;

        // from <- 정적 팩토리 메서드 패턴 ("주어진 데이터를 기반으로 새로운 객체를 생성한다"는 의미로 사용)
        // 여기서는 article을 기반으로 CreateArticleResponseDTO 객체를 생성한다고 받아들일 수 있음
        public static CreateArticleResponseDTO from(Article article) {
            return CreateArticleResponseDTO.builder()
                    .id(article.getId())
                    .createdAt(article.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    /** 게시물 조회 ResponseDTO */
    public static class ArticlePreviewDTO {
        private Long id;
        private String title;
        private String content;
        private int likeNum;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ArticlePreviewDTO from(Article article) {
            return ArticlePreviewDTO.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .likeNum(article.getLikeNum())
                    .createdAt(article.getCreatedAt())
                    .updatedAt(article.getUpdatedAt())
                    .build();
        }
    }

    /** 페이지네이션된 게시물 목록 조회 ResponseDTO*/
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticlePreviewListDTO {
        private List<ArticlePreviewDTO> articles; // 게시글 목록 리스트
        private boolean hasNext; // 다음페이지가 있는지 여부를 나타냄
        private Long cursor; // 페이지네이션의 기준! 현재 페이지의 마지막 게시글의 ID를 커서로 사용

        // Slice는 Spring Data JPA에서 제공하는 페이징 처리 인터페이스로,
        // 다음 페이지가 있는지 여부를 포함하여 현재 페이지의 데이터만을 가져올 수 있는 기능을 제공함
        // Page 인터페이스와 유사하지만, 총 데이터 개수를 조회하지 않는다는 점에서 차이가 있음
        public static ArticlePreviewListDTO from(Slice<Article> articles) {
            return ArticlePreviewListDTO.builder()
                    // articles.getContent()가 빈 리스트일 경우 새로운 빈 ArrayList 생성
                    // 비어있지 않을 경우 Article Entity 목록을 stream을 이용해 ArticlePreviewDTO 로 변환 !
                    // 이 둘을 각각 리스트로 만들어서 articles에 넣는다
                    .articles(articles.getContent().isEmpty() ? new ArrayList<>(): articles.getContent().stream().map(ArticlePreviewDTO::from).toList())
                    // Slice 객체의 hasNext() 메서드를 호출해 다음 페이지가 있는지 여부를 확인하고, hasNext 필드에 설정
                    .hasNext(articles.hasNext())
                    // 리스트가 비어있다면 cursor를 0으로 설정
                    // 리스트에 데이터가 있다면, 마지막 게시글의 ID를 가져와 cursor 필드에 설정
                    // 이렇게 설정된 cursor는 다음 페이지 조회 시 사용됨
                    // -1 하는거는 리스트의 마지막 요소의 인덱스를 얻으려고 하는 것임
                    // 그 인덱스로 마지막 게시물 객체를 가져온 뒤 getId()로 해당 게시물의 ID 가져오는 것!
                    .cursor(articles.getContent().isEmpty() ? 0 : articles.getContent().get(articles.getContent().size() - 1).getId())
                    .build();
        }
    }
}
