package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/replies")
    @Operation(summary = "댓글 생성 API", description = "댓글 생성하는 API")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {

        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResponseDTO(reply));
    }

    @GetMapping("/replies")
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회화는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies() {

        List<Reply> replies = replyQueryService.getReplies();

        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    @GetMapping("/replies/{replyId}")
    @Operation(summary = "댓글 조회 API", description = "댓글 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {

        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @PutMapping("/replies/{replyId}")
    @Operation(summary = "댓글 내용 업데이트 API", description = "댓글 내용 업데이트하는 API")
    public CustomResponse<ReplyResponseDTO.UpdateReplyResponseDTO> updateReply
            (@PathVariable("replyId") Long replyId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {

//        ReplyResponseDTO.UpdateReplyResponseDTO updatedDto = replyCommandService.updateReply(replyId, dto);
        Reply reply = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(ReplyConverter.toUpdateReplyResponseDTO(reply));
    }

    @DeleteMapping("/replies/{replyId}")
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제하는 API")
    public CustomResponse<?> deleteReply(@PathVariable("replyId") Long replyId) {

        Long id = replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(replyCommandService.deleteReply(id));
    }

    @GetMapping("/articles/{articleId}/replies")
    @Operation(summary = "댓글 offset 페이지네이션 API", description = "댓글 offset 페이지네이션")
    public CustomResponse<ReplyResponseDTO.ReplyPageListDTO> getRepliesByArticleUsingOffset(
            @PathVariable("articleId") Long articleId,
            @RequestParam(defaultValue = "0") int page, // 현재 페이지
            @RequestParam(defaultValue = "7") int size // 크기
            ) {
        log.info(String.valueOf(page));
        log.info(String.valueOf(size));
        System.out.println("hello");

        Page<Reply> replies = replyQueryService.getRepliesByArticleId(articleId, page, size); // 댓글 얻고

        return CustomResponse.onSuccess(ReplyConverter.toReplyPageListDTO(replies));
    }
}
