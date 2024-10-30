package com.example.umc7th.controller;


import com.example.umc7th.converter.ReplyConverter;
import com.example.umc7th.dto.request.ReplyRequestDto;
import com.example.umc7th.dto.response.ReplyResponseDto;
import com.example.umc7th.entity.Reply;
import com.example.umc7th.global.apipayload.CustomResponse;
import com.example.umc7th.global.apipayload.success.GeneralSuccessCode;
import com.example.umc7th.service.command.ReplyCommandService;
import com.example.umc7th.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "댓글 API", description = "댓글 관련 API")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;


    @PostMapping("/articles/{articleId}/replies")
    @Operation(method = "POST",
            summary = "댓글 등록 API",
            description = "ReplyRequestDto 형태로 body를 받아 해당 articleId에 맞는 게시글에 댓글을 등록합니다. 만들어진 댓글 id를 반환합니다.")
    public CustomResponse<Map<String, Long>> createReply(@RequestBody ReplyRequestDto.CreateReplyRequestDto replyRequestDto,
                                                         @PathVariable Long articleId){
        Long replyId = replyCommandService.createReply(replyRequestDto, articleId);

        Map<String, Long> result = new HashMap<>();
        result.put("replyId", replyId);

        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED_201, result);
    }

    @GetMapping("/articles/replies")
    @Operation(method = "GET",
            summary = "댓글 전체 조회 API",
            description = "게시글과 상관없이 전체 댓글들을 ReplyResponseDto 리스트형태로 반환합니다.")
    public CustomResponse<ReplyResponseDto.ReplyPreviewListDto> getReplies(){
        ReplyResponseDto.ReplyPreviewListDto replyResponseDtos = replyQueryService.getReplies();
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, replyResponseDtos);
    }

    @GetMapping("/articles/replies/{replyId}")
    @Operation(method = "GET",
            summary = "단일 댓글 조회 API",
            description = "replyId에 해당하는 댓글을 단일 조회합니다.")
    public CustomResponse<ReplyResponseDto.ReplyPreviewDto> getReplies(@PathVariable("replyId") Long replyId){
        ReplyResponseDto.ReplyPreviewDto replyResponseDto = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, replyResponseDto);
    }

    @GetMapping("/articles/{articleId}/replies")
    @Operation(method = "GET",
            summary = "댓글 페이지 조회",
            description = "pageNum, pageSize를 파라미터로 받아 articleId에 해당하는 게시글의 댓글 페이지단위 조회")
    public CustomResponse<ReplyResponseDto.ReplyPagePreviewListDto> getRepliesPage(
            @PathVariable("articleId") Long articleId,
            @RequestParam int pageNum,
            @RequestParam int pageSize)
    {
        Page<Reply> page = replyQueryService.getRepliesByArticle(pageNum, pageSize, articleId);
        ReplyResponseDto.ReplyPagePreviewListDto result = ReplyConverter.from(page);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }

    @PutMapping("/articles/replies/{replyId}")
    @Operation(method = "PUT",
            summary = "댓글 수정 API",
            description = "replyId에 해당하는 댓글을 수정합니다.")
    public CustomResponse<ReplyResponseDto.ReplyPreviewDto> updateReply(@PathVariable Long replyId,
                                                                        @RequestBody ReplyRequestDto.UpdateReplyRequestDto dto){
        ReplyResponseDto.ReplyPreviewDto result = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }

    @DeleteMapping("/articles/replies/{replyId}")
    @Operation(method = "DELETE",
            summary = "댓글 삭제 API",
            description = "replyId에 해당하는 댓글을 삭제합니다.")
    public CustomResponse<String> deleteReply(@PathVariable Long replyId){
        replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(GeneralSuccessCode.NO_CONTENT_204, "해당 댓글이 삭제되었습니다.");
    }
}
