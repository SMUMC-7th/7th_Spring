package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.response.ReplyResDto;
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
    public ReplyResDto.ReplyPreviewListDto getRepliesByArticle(Long articleId) {
        //controller에서 받은 articleId로 게시글 확인 -> 없으면 예외처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        //조회 한 게시글에 달린 reply 리스트 조회
        List<Reply> replies = replyRepository.findAllByArticle(article);

        //Converter를 통해 리스트 전체를 DTO로 변환 후 반환
        return ReplyConverter.toReplyPreviewListDto(replies);
    }
}
