package com.example.umc7th.reply.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.dto.ReplyResponseDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.service.command.ReplyCommandService;
import com.example.umc7th.reply.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reply 관련 컨트롤러")
public class ReplyController {
    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping("/reply")
    @Operation(summary = "댓글 생성 API", description = "댓글을 생성하는 api")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyResponseDTO.CreateReplyResponseDTO.from(reply));
    }

    @GetMapping("/replies")
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies() {
        List<Reply> replies = replyQueryService.getReplies();
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPreviewListDTO.from(replies));
    }

    @GetMapping("/reply/{replyId}")
    @Operation(summary = "댓글 조회 API", description = "댓글 하나 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPreviewDTO.from(reply));
    }

    @PatchMapping("/reply/{replyId}")
    @Operation(summary = "reply 수정 API", description = "reply를 수정하는 api")
    public CustomResponse<ReplyResponseDTO.UpdateReplyResponseDTO> updateReply(@PathVariable("replyId") Long replyId,
                                                                               @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply updateReply = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(ReplyResponseDTO.UpdateReplyResponseDTO.from(updateReply));
    }

    @DeleteMapping("/reply/{replyId}")
    @Operation(summary = "reply 삭제 API", description = "reply를 삭제하는 api")
    public CustomResponse<ReplyResponseDTO.DeleteReplyResponseDTO> deleteReply(@PathVariable("replyId") Long replyId) {
        Reply deleteReply = replyQueryService.getReply(replyId);
        replyCommandService.deleteReply(deleteReply.getId());
        return CustomResponse.onSuccess(ReplyResponseDTO.DeleteReplyResponseDTO.from(deleteReply));
    }

    @GetMapping("/article/{articleId}/replies")
    @Operation(summary = "댓글 offset 페이지네이션 API", description = "댓글 offset 페이지네이션")
    public CustomResponse<ReplyResponseDTO.ReplyPagePreviewListDTO> getRepliesByArticle(
            @PathVariable("articleId") Long articleId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size ) {
//        PageRequest pageRequest = PageRequest.of(page, size); // 이렇게 PageRequest에 담아서 요청을 보낼 수 있음!
        Page<Reply> replies = replyQueryService.getRepliesByArticleId(articleId, page, size);
        return CustomResponse.onSuccess(ReplyResponseDTO.ReplyPagePreviewListDTO.from(replies));
    }

}
