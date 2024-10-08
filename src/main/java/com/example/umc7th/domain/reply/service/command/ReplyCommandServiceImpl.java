package com.example.umc7th.domain.reply.service.command;



import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        Article article=articleRepository.findById(dto.getArticleId()).orElseThrow(() -> new EntityNotFoundException("게시물 없음"));
        return replyRepository.save(
                // Builder 패턴 사용
                Reply.builder()
                        .content(dto.getContent())
                        .article(article)
                        .build()
        );
    }


}
