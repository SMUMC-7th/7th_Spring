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
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping
    @Operation(summary = "댓글 생성 API", description = "댓글 생성하는 API")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyResponseDTO.CreateReplyResponseDTO.from(reply));
    }

    @GetMapping
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies() {
        List<Reply> replies = replyQueryService.getReplies();
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPreviewListDTO.from(replies));
    }

    @GetMapping("/{replyId}")
    @Operation(summary = "댓글 조회 API", description = "댓글 하나 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPreviewDTO.from(reply));
    }

    @PutMapping("/{replyId")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReply(@PathVariable("replyId") Long replyId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPreviewDTO.from(reply));
    }

    @DeleteMapping("/{replyId}")
    public CustomResponse<Long> deleteReply(@PathVariable("replyId") Long replyId) {
        Long id =replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(id);
    }

}
