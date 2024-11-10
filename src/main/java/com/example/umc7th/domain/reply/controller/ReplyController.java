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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 API")
@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/replies")
    @Operation(summary = "댓글 생성 API", description = "댓글 생성하는 API")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyResponseDTO.CreateReplyResponseDTO.from(reply));
    }

    @GetMapping("/articles/{articleId}/replies")
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewPageDTO> getReplies(@PathVariable Long articleId,
                                                                           @RequestParam("page") Integer page,
                                                                           @RequestParam(value = "offset", defaultValue = "10") Integer offset) {
        Page<Reply> replies = replyQueryService.getReplies(articleId, page, offset);
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPreviewPageDTO.from(replies));
    }

    @GetMapping("/replies/{replyId}")
    @Operation(summary = "댓글 조회 API", description = "댓글 하나 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPreviewDTO.from(reply));
    }

    @PutMapping("/replies/{replyId}")
    @Operation(summary = "댓글 수정 API", description = "댓글 수정하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReply(@PathVariable("replyId") Long replyId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPreviewDTO.from(reply));
    }

    @DeleteMapping("/replies/{replyId}")
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제하는 API")
    public CustomResponse<Long> deleteReply(@PathVariable("replyId") Long replyId) {
        Long id = replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(id);
    }


}
