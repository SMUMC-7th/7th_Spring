package com.example.umc7th.reply.service.query;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.dto.ReplyResDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;


    @Override
    public List<ReplyResDTO> getRepliesByArticleId(Long articleId) {
        //articleId로 해당 id의 모든 댓글 조회
        List<Reply> replies = replyRepository.findAllByArticleId(articleId);

        //Reply Entity -> ReplyResDTO
        return replies.stream()
                .map(ReplyResDTO::new)
                .collect(Collectors.toList());
    }
}
