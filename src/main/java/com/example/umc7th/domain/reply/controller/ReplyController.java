package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "댓글 API")
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/articles/{articleId}")
    @Operation(method = "POST", summary = "댓글 생성 API", description = "articleId로 조회한 게시글에 댓글을 생성하는 API")
    public ResponseEntity<CustomResponse<ReplyResponseDTO.CreateReplyResponseDto>> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        ReplyResponseDTO.CreateReplyResponseDto responseDto = replyCommandService.createReply(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

    @GetMapping("/articles/{articleId}/replies")
    @Operation(method = "GET", summary = "게시글 별 댓글 조회 API", description = "articleId로 조회한 게시글의 댓글 전체 조회")
    public CustomResponse<List<ReplyResponseDTO.CreateReplyResponseDto>> getRepliesByArticleId(@PathVariable("articleId") Long articleId) {
        List<ReplyResponseDTO.CreateReplyResponseDto> replies = replyQueryService.getRepliesByArticle(articleId);
        return CustomResponse.onSuccess(replies);
    }

}

