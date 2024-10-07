package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "댓글 API")
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/articles/{articleId}/replies")
    @Operation(method = "POST", summary = "댓글 생성 API")
    public ResponseEntity<CustomResponse<Reply>> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(reply));
    }

    @GetMapping("/articles/{articleId}/replies")
    @Operation(method = "GET", summary = "게시글 별 댓글 조회 API")
    public CustomResponse<List<Reply>> getReplies(@PathVariable("articleId") Long articleId) {
        List<Reply> replies = replyQueryService.getRepliesByArticle(articleId);
        return CustomResponse.onSuccess(replies);
    }
}

