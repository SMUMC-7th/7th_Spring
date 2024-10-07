package com.example.umc7th.domain.article.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

public class ArticleRequestDTO {

    public record CreateArticleRequestDTO(
        String title,
        String content){
    }
}
