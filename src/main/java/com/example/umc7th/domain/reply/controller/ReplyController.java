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

    @Operation(summary = "댓글 생성", description = "articleId에 해당하는 게시글에 댓글을 생성합니다.")
    @PostMapping("")
    public ResponseEntity<CustomResponse<ReplyResDto.CreateReplyResponseDto>> createReply(
            @RequestBody ReplyReqDto.CreateReplyRequestDto requestDto) {
        ReplyResDto.CreateReplyResponseDto responseDto = replyCommandService.createReply(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

    @Operation(summary = "게시글 별 댓글 조회", description = "articleId에 해당하는 게시글에 달린 모든 댓글을 조회합니다.")
    @GetMapping("/article/{articleId}")
    public CustomResponse<ReplyResDto.ReplyPreviewListDto> getRepliesByArticleId(@PathVariable Long articleId) {
        ReplyResDto.ReplyPreviewListDto replies = replyQueryService.getRepliesByArticle(articleId);
        return CustomResponse.onSuccess(replies);
    }

    @Operation(summary = "댓글 수정", description = "articleId에 해당하는 게시글의 특정 댓글(replyId)을 수정합니다.")
    @PatchMapping("/{replyId}")
    public CustomResponse<String> updateReply(@PathVariable Long replyId,
                                              @RequestBody ReplyReqDto.UpdateReplyRequestDto requestDto) {
        replyCommandService.updateReply(replyId, requestDto);
        return CustomResponse.onSuccess("댓글 수정이 완료되었습니다.");
    }

    @Operation(summary = "댓글 삭제", description = "articleId에 해당하는 게시글의 특정 댓글(replyId)을 삭제합니다. (소프트 삭제)")
    @DeleteMapping("/{replyId}")
    public CustomResponse<String> deleteReply(@PathVariable Long replyId) {
        replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess("댓글 삭제가 완료되었습니다.");
    }
}
