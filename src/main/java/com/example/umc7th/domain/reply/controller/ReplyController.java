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

    /** 댓글 생성 API */
    @PostMapping
    @Operation(summary = "댓글 생성 API", description = "댓글 생성하는 API")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResponseDTO(reply));
    }

    /** 댓글 전체 조회 API */
    @GetMapping("/articles/{articleId}")
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies(@PathVariable Long articleId,
                                                                           @RequestParam("page") Integer page,
                                                                           @RequestParam(value = "offset", defaultValue = "10") Integer offset) {
        // 페이지와 오프셋을 기반으로 특정 게시글의 댓글 목록을 조회하여 응답 DTO로 변환
        Page<Reply> replies = replyQueryService.getReplies(articleId, page, offset);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }*/

    /**
     * 댓글 전체 조회 API (Offset 기반 페이지네이션)
     * @param page 페이지 번호
     * @param size 한 페이지당 댓글 수
     * @return 조회된 페이지네이션 댓글 목록을 CustomResponse로 반환
     */
    @GetMapping
    @Operation(summary = "댓글 전체 조회 API", description = "Offset 기반 페이지네이션을 통해 댓글 전체를 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Reply> replyPage = replyQueryService.getRepliesWithPagination(page, size);

        // 응답 DTO 변환 및 페이지네이션 정보 설정
        ReplyResponseDTO.ReplyPreviewListDTO response = ReplyConverter.toReplyPreviewListDTO(
                replyPage.getContent(),
                replyPage.getSize(),
                replyPage.getNumber(),
                (int) replyPage.getTotalElements()
        );

        return CustomResponse.onSuccess(response);
    }


    /** 댓글 하나 조회 API */
    @GetMapping("/{replyId}")
    @Operation(summary = "댓글 조회 API", description = "댓글 하나 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    /** 댓글 수정 API */
    @PutMapping("/{replyId}")
    @Operation(summary = "댓글 수정 API", description = "댓글 수정하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReply(@PathVariable("replyId") Long replyId,
                                                                        @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    /** 댓글 삭제 API */
    @DeleteMapping("/{replyId}")
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제하는 API")
    public CustomResponse<Long> deleteReply(@PathVariable("replyId") Long id) {
        return CustomResponse.onSuccess(replyCommandService.deleteReply(id));
    }
}