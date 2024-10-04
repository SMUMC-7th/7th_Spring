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
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServicelmpl implements ReplyQueryService{

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<ReplyResDto.CreateReplyResponseDto> getRepliesByArticle(Long articleId) {
        //controller에서 받은 articleId로 게시글 확인 -> 없으면 예외처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        //조회 한 게시글에 달린 reply 리스트 조회
        List<Reply> replies = replyRepository.findByArticle(article);

        //조회 한 reply 리스트들 stream을 이용하여 Entity -> DTO로 변환 후 반환
        return replies.stream()
                .map(ReplyConverter::from)
                .collect(Collectors.toList());
    }
}
