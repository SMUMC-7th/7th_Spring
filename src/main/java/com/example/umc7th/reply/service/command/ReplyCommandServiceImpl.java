package com.example.umc7th.reply.service.command;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.dto.ReplyReqDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService{

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;


    @Override
    public void saveReply(ReplyReqDTO replyReq) {
        Optional<Article> articleOptional = articleRepository.findById(replyReq.getArticleId());

        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();

            Reply reply = Reply.builder()
                    .content(replyReq.getContent())
                    .article(article)
                    .build();

            replyRepository.save(reply);

        } else {
            throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
        }
    }
}
