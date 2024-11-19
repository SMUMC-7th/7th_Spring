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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles") //공통으로 빼기
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    //댓글 생성
    @PostMapping("/{articleId}/replies")
    @Operation(summary = "댓글 생성 API", description = "댓글 생성")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResponseDTO(reply));
    }

    //모든 댓글 조회(게시물 상관x)
    @GetMapping("/replies")
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies() {
        List<Reply> replies = replyQueryService.getReplies();
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    //댓글 단일 조회
    @GetMapping("/{articleId}/replies/{replyId}")
    @Operation(summary = "댓글 단일 조회 API", description = "댓글 단일 조회")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    //댓글 수정
    @PutMapping("/{articleId}/replies/{replyId}")
    @Operation(summary = "댓글 수정 API", description = "댓글 수정")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReply(@PathVariable("replyId") Long replyId,
                                                                        @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    //댓글 삭제
    @DeleteMapping("/{articleId}/replies/{replyId}")
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제")
    public CustomResponse<Void> deleteReply(@PathVariable Long replyId){
        replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(null); //converter 사용 안 함
    }

    //offset기반 특정 게시물 댓글 페이지네이션
    @GetMapping("/{articleId}/replies/")
    @Operation(summary = "댓글 페이지네이션", description = "offset기반 댓글 페이지네이션")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getRepliesByArticleUsingOffset(
            @PathVariable Long articleId,
            @RequestParam(required = false, defaultValue = "0") Long lastId,
            Pageable pageable) {

        // 댓글 가져오기
        Page<Reply> replies = replyQueryService.getRepliesByArticleIdWithOffset(articleId, lastId, pageable);
        //dto 변환
        ReplyResponseDTO.ReplyPreviewListDTO replyPreviewListDTO = ReplyResponseDTO.ReplyPreviewListDTO.fromReplies(replies.getContent());

        return CustomResponse.onSuccess(replyPreviewListDTO);

    }



}
