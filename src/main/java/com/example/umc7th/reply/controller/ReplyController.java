package com.example.umc7th.reply.controller;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.reply.dto.AddReplyRequestDTO;
import com.example.umc7th.reply.dto.ReplyDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.service.command.ReplyCommandService;
import com.example.umc7th.reply.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;
    private final ArticleQueryService articleQueryService;

    @PostMapping("/articles/{articleId}/replies")
    public CustomResponse<Reply> createReply(@PathVariable("articleId") Long articleId, @RequestBody AddReplyRequestDTO dto) {

        // 각각의 articleId에서 reply(댓글)를 생성해야 할텐데 어떻게 articleId를 찾아와야할지 모르겠음
        // 지정된 articleId에 대한 Article을 가져와야함 -> Article 엔티티를 댓글 요청dto와 함께 createReply 메서드에 전달해야함

        Article article = articleQueryService.getArticle(articleId);

        Reply reply = replyCommandService.createReply(dto, article);

        return CustomResponse.onSuccess(reply);
    }

    @GetMapping("/articles/{articleId}/replies")
    public CustomResponse<List<ReplyDTO>> getReplies(@PathVariable("articleId") Long articleId) {

        List<Reply> replies = replyQueryService.getReplies(articleId);
        for (Reply reply : replies) {
            System.out.println("reply = " + reply.getContent());
        }

        /*
            return CustomResponse.onSuccess(replies); //-> json.AbstractJackson2HttpMessageConverter 직렬화? 문제 발생
            1. replies 자체를 넘기니 직렬화 문제 발생(CustomResponse의 result 필드가 직렬화 되지 않을 수 있다고 함)
            -> ManyToOne에서의 LAZY 설정에 있어서 직렬화 시점에 초기화되지 않아 문제 발생할 수 있음
            -> Reply들을(list) DTO로 넘겨버림
        */
        

        List<ReplyDTO> replyDTOList = replyQueryService.getReplies(articleId)
                .stream()
                .map(ReplyDTO::from)
                .toList();

        return CustomResponse.onSuccess(replyDTOList);
    }
}
