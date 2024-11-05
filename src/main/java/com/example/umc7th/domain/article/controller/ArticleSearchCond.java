package com.example.umc7th.domain.article.controller;

public enum ArticleSearchCond {

    ID("id"),
    CREATED_AT("생성날짜"),
    LIKE_NUM("좋아요 수"),
    ;

    private final String description;

    ArticleSearchCond(String description) {
        this.description = description;
    }
}
