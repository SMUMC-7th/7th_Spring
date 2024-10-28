package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService{
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<ReplyResponseDTO.ResponsePreviewDto> getRepliesByArticle(Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));

        List<Reply> replies = replyRepository.findByArticleAndActiveTrue(article);
        return ReplyConverter.fromList(replies);

    }

}
