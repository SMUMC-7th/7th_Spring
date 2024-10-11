package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "댓글 API")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping("/articles/{articleId}/replies")
    @Operation(summary = "댓글 생성 API")
    public CustomResponse<Reply> createArticle(@PathVariable("articleId") Long articleId,
                                               @RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto, articleId);
        return CustomResponse.onSuccess(reply);
    }

    @GetMapping("/replies/{replyId}")
    @Operation(summary = "단일 댓글 조회 API")
    public CustomResponse<ReplyResponseDTO> getReply(@PathVariable("replyId") Long replyId) {
        ReplyResponseDTO reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(reply);
    }

    @GetMapping("/replies")
    @Operation(summary = "전체 댓글 조회 API")
    public CustomResponse<List<ReplyResponseDTO>> getArticles() {
        List<ReplyResponseDTO> replies = replyQueryService.getReplies();
        return CustomResponse.onSuccess(replies);
    }

}
