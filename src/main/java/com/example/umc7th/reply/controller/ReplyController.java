package com.example.umc7th.reply.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.dto.ReplyResponseDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.service.command.ReplyCommandService;
import com.example.umc7th.reply.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping("/articles/{articleId}/replies")
    public CustomResponse<Reply> createArticle(@PathVariable("articleId") Long articleId,
                                               @RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto, articleId);
        return CustomResponse.onSuccess(reply);
    }

    @GetMapping("/replies/{replyId}")
    public CustomResponse<ReplyResponseDTO> getReply(@PathVariable("replyId") Long replyId) {
        ReplyResponseDTO reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(reply);
    }

    @GetMapping("/replies")
    public CustomResponse<List<ReplyResponseDTO>> getArticles() {
        List<ReplyResponseDTO> replies = replyQueryService.getReplies();
        return CustomResponse.onSuccess(replies);
    }

}
