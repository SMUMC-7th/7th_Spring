package com.example.umc7th.domain.reply.controller;

import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 댓글 관련 api 제공하는 controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
@Tag(name = "댓글 API")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    /**
     * 댓글 생성 api
     * @param dto (생성할 댓글 정보)
     * @return 생성된 댓글 정보를 customResponse로 반환
     */
    @PostMapping
    @Operation(summary = "댓글 생성 API", description = "댓글 생성하는 API")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        // 댓글 생성
        Reply reply = replyCommandService.createReply(dto);
        // 성공 응답 반환
        return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResponseDTO(reply));
    }

    /**
     * 댓글 전체 조회 api
     * @return 조회된 전체 댓글 정보를 customResponse로 반환
     */
    @GetMapping
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies() {
        List<Reply> replies = replyQueryService.getReplies();
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    /**
     * 특정 댓글 조회 api
     * @return 조회된 댓글 정보를 customResponse로 반환
     */
    @GetMapping("/{replyId}")
    @Operation(summary = "댓글 조회 API", description = "댓글 하나 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    /**
     * 댓글 전체 수정 API (PUT)
     * @param replyId (수정할 댓글의 Id)
     * @param dto (수정할 댓글 정보)
     * @return 수정된 댓글 정보를 CustomResponse로 반환
     */
    @PutMapping("/{replyId}")
    @Operation(summary = "댓글 전체 수정 API", description = "댓글 전체 수정하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReplyPut(@PathVariable("replyId") Long replyId,
                                                                           @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply updatedReply = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(updatedReply));
    }

    /**
     * 댓글 삭제 API
     * @param replyId (삭제할 댓글의 Id)
     * @return 성공 응답 반환
     */
    @DeleteMapping("/{replyId}")
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제하는 API")
    public CustomResponse<ReplyResponseDTO.DeleteReplyResponseDTO> deleteReply(@PathVariable("replyId") Long replyId) {
        replyCommandService.deleteReply(replyId);
        ReplyResponseDTO.DeleteReplyResponseDTO responseDto = ReplyConverter.toDeleteReplyResponseDTO(replyId);
        return CustomResponse.onSuccess(responseDto);
    }

}