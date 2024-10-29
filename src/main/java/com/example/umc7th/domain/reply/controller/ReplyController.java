package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
@Tag(name = "댓글 API")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping
    @Operation(summary = "댓글 생성 API")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResponseDTO(reply));
    }

    @GetMapping
    @Operation(summary = "전체 댓글 조회 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies() {
        List<Reply> replies = replyQueryService.getReplies();
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    @GetMapping("{replyId}")
    @Operation(summary = "단일 댓글 조회 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @PutMapping()
    @Operation(summary = "댓글 수정 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReply(@RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyCommandService.updateReply(dto);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @DeleteMapping("{replyId}")
    @Operation(summary = "댓글 삭제 API")
    public CustomResponse<Long> deleteReply(@PathVariable("replyId") Long replyId) {
        Long id = replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(id);
    }

    @GetMapping("/article/{articleId}")
    @Operation(summary = "생성날짜 순 정렬, Offset 기반 댓글 조회 API")
    public CustomResponse<ReplyResponseDTO.ReplyPagePreviewListDTO> getRepliesByArticleId(
            @PathVariable Long articleId,
            @RequestParam int page,
            @RequestParam int size
            ) {
        Page<Reply> replies = replyQueryService.getRepliesForArticleOrderByCreatedAt(articleId, page, size);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPagePreviewListDTO(replies));
    }

}
