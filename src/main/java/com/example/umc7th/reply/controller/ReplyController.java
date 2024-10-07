package com.example.umc7th.reply.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.service.command.ReplyCommandService; // Import the command service
import com.example.umc7th.reply.service.query.ReplyQueryService; // Import the query service
import com.example.umc7th.reply.dto.ReplyRequestDTO; // Ensure the DTO is imported
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping
    public CustomResponse<Reply> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(reply);
    }

    @GetMapping("/{replyId}")
    public CustomResponse<Optional<Reply>> getReply(@PathVariable Long replyId) {
        Optional<Reply> reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(reply);
    }

    @GetMapping
    public CustomResponse<List<Reply>> getReplies(@RequestParam Long articleId) {
        List<Reply> replies = replyQueryService.getRepliesByArticleId(articleId);
        return CustomResponse.onSuccess(replies);
    }
}