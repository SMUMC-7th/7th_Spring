package com.example.umc7th.controller;


import com.example.umc7th.dto.request.ReplyRequestDto;
import com.example.umc7th.dto.response.ReplyResponseDto;
import com.example.umc7th.global.apipayload.CustomResponse;
import com.example.umc7th.global.apipayload.success.GeneralSuccessCode;
import com.example.umc7th.service.command.ReplyCommandService;
import com.example.umc7th.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/replies")
    @Operation(method = "GET",
            summary = "댓글 전체 조회 API",
            description = "게시글과 상관없이 전체 댓글들을 ReplyResponseDto 리스트형태로 반환합니다.")
    public CustomResponse<List<ReplyResponseDto>> getReplies(){
        List<ReplyResponseDto> replyResponseDtos = replyQueryService.getReplies();
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, replyResponseDtos);
    }

    @GetMapping("/replies/{replyId}")
    @Operation(method = "GET",
            summary = "단일 댓글 조회 API",
            description = "replyId에 해당하는 댓글을 단일 조회합니다.")
    public CustomResponse<ReplyResponseDto> getReplies(@PathVariable("replyId") Long replyId){
        ReplyResponseDto replyResponseDto = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, replyResponseDto);
    }
}
