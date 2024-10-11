package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping("/articles/{articleId}/replies")
    @Operation(summary = "댓글 생성")
    public CustomResponse<ReplyResponseDTO.CreateReplyResultDTO> createReply(
            @PathVariable Long articleId,
            @RequestBody ReplyRequestDTO.CreateReplyDTO createReplyDTO) {

        Reply reply = replyCommandService.createReply(articleId, createReplyDTO);

        return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResultDTO(reply));
    }

    @PutMapping("/articles/{articleId}/replies/{replyId}")
    @Operation(summary = "댓글 수정")
    public CustomResponse<String> updateReply(
            @PathVariable Long articleId,
            @PathVariable Long replyId,
            @RequestBody ReplyRequestDTO.UpdateReplyDTO updateReplyDTO) {

        replyCommandService.updateReply(articleId, replyId, updateReplyDTO);

        return CustomResponse.onSuccess("댓글을 수정에 성공하였습니다.");
    }

    @DeleteMapping("/articles/replies/{replyId}")
    @Operation(summary = "댓글 soft 삭제")
    public CustomResponse<String> deleteReply(@PathVariable Long replyId) {

        replyCommandService.softDeleteReply(replyId);

        return CustomResponse.onSuccess("댓글을 soft 삭제에 성공하였습니다.");
    }

    @GetMapping("/articles/replies/{replyId}")
    @Operation(summary = "댓글 하나 조회")
    public CustomResponse<ReplyResponseDTO.ReplyViewDTO> getReply(@PathVariable Long replyId) {

        Reply reply = replyQueryService.getReply(replyId);

        return CustomResponse.onSuccess(ReplyConverter.toReplyViewDTO(reply));
    }

    @GetMapping("/articles/{articleId}/replies")
    @Operation(summary = "특정 게시글의 댓글 전체 조회")
    public CustomResponse<ReplyResponseDTO.ReplyViewListDTO> getReplies(@PathVariable Long articleId) {

        List<Reply> replies = replyQueryService.getReplies(articleId);

        return CustomResponse.onSuccess(ReplyConverter.toReplyViewListDTO(replies));
    }
}
