package com.example.umc7th.domain.reply.controller;


import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    //댓글 생성
    @PostMapping("/articles/{articleId}/replies")
    public CustomResponse<Reply> createArticle(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(reply);
    }

    //게시물의 모든 댓글 조회
    @GetMapping("/articles/{articleId}/replies")
    public CustomResponse<List<Reply>> getReplies(@PathVariable("articleId") Long articleId) {
        List<Reply> replies=replyQueryService.getRepliesByArticle(articleId);
        return CustomResponse.onSuccess(replies);
    }
}
