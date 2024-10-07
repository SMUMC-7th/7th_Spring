package com.example.umc7th.article.service.command;

import org.springframework.stereotype.Service;
import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

// Service로 사용하겠다고 명시 (빈 주입)
@Service
// Transactional을 사용하겠다고 명시. 모든 메소드가 하나의 Transaction 단위로 동작, 단일 메소드에도 선언 가능
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
		    // 데이터 베이스에 DTO로 만든 객체 저장하고 저장된 객체 반환
        return articleRepository.save(
			        // Builder 패턴 사용
                Article.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .build()
        );
    }
}