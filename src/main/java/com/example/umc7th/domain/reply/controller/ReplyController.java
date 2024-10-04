package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.domain.reply.dto.request.ReplyReqDto;
import com.example.umc7th.domain.reply.dto.response.ReplyResDto;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    // 댓글 생성
    @PostMapping("")
    public ResponseEntity<CustomResponse<ReplyResDto.CreateReplyResponseDto>> createReply(
            @RequestBody ReplyReqDto.CreateReplyRequestDto requestDto) {

        ReplyResDto.CreateReplyResponseDto responseDto = replyCommandService.createReply(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

    // 게시글 별 댓글 조회
    @GetMapping("/article/{articleId}")
    public CustomResponse<List<ReplyResDto.CreateReplyResponseDto>> getRepliesByArticleId(
            @PathVariable Long articleId) {

        List<ReplyResDto.CreateReplyResponseDto> replies = replyQueryService.getRepliesByArticle(articleId);
        return CustomResponse.onSuccess(replies);
    }
}
