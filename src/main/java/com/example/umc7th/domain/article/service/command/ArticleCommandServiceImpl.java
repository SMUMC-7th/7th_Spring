package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.request.ArticleReqDto;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public ArticleResDto.CreateArticleResponseDto createArticle(ArticleReqDto.CreateArticleRequestDto requestDto) {
        Article newArticle = articleRepository.save(ArticleConverter.toArticle(requestDto));
        return ArticleConverter.toCreateArticleResponseDto(newArticle);
    }

    @Override
    public void updateArticle(Long articleId, ArticleReqDto.UpdateArticleRequestDto requestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.update(requestDto.title(), requestDto.content());
    }

    @Override
    public void updateArticleTitle(Long articleId, ArticleReqDto.UpdateArticleTitleRequestDto requestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.setTitle(requestDto.title());
    }

    @Override
    public void updateArticleContent(Long articleId, ArticleReqDto.UpdateArticleContentRequestDto requestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.setContent(requestDto.content());
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        // 해당 article의 reply들을 모두 조회해서 reply들도 softdelete 시킴
        replyRepository.findAllByArticle(article)
                .forEach(Reply::softDelete);
        //reply들을 모두 softdelete 시킨 후 article도 softdelete 진행
        article.softDelete();
    }
}
