package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.domain.reply.dto.request.ReplyReqDto;
import com.example.umc7th.domain.reply.dto.response.ReplyResDto;
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
@RequestMapping("/api/replies")
@Tag(name = "댓글 API", description = "댓글 관련 API입니다.")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    // 댓글 생성
    @Operation(summary = "댓글 생성", description = "request로 넘긴 articleId로 조회한 게시글에 댓글을 생성합니다.")
    @PostMapping("")
    public ResponseEntity<CustomResponse<ReplyResDto.CreateReplyResponseDto>> createReply(
            @RequestBody ReplyReqDto.CreateReplyRequestDto requestDto) {

        ReplyResDto.CreateReplyResponseDto responseDto = replyCommandService.createReply(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

    // 게시글 별 댓글 조회
    @Operation(summary = "댓글 생성", description = "URL로 넘긴 articleId로 조회한 게시글의 댓글을 전체 조회합니다.")
    @GetMapping("/article/{articleId}")
    public CustomResponse<List<ReplyResDto.CreateReplyResponseDto>> getRepliesByArticleId(
            @PathVariable Long articleId) {

        List<ReplyResDto.CreateReplyResponseDto> replies = replyQueryService.getRepliesByArticle(articleId);
        return CustomResponse.onSuccess(replies);
    }
}
