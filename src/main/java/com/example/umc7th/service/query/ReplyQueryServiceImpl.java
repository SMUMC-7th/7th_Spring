package com.example.umc7th.service.query;


import com.example.umc7th.converter.ReplyConverter;
import com.example.umc7th.dto.response.ReplyResponseDto;
import com.example.umc7th.entity.Article;
import com.example.umc7th.entity.Reply;
import com.example.umc7th.exception.code.ArticleErrorCode;
import com.example.umc7th.exception.code.ReplyErrorCode;
import com.example.umc7th.exception.exception.ArticleException;
import com.example.umc7th.exception.exception.ReplyException;
import com.example.umc7th.repository.ArticleRepository;
import com.example.umc7th.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyResponseDto.ReplyPreviewListDto getReplies() {
        return ReplyConverter.fromList(replyRepository.findAll());
    }

    @Override
    public ReplyResponseDto.ReplyPreviewDto getReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND));
        return ReplyConverter.from(reply);
    }

    @Override
    public Page<Reply> getRepliesByArticle(int pageNum, int pageSize, long articleId) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Article article = articleRepository.findByIdAndActiveTrue(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return replyRepository.findAllBy(pageable, article);
    }
}
