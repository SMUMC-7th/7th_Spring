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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
@Tag(name = "댓글 API")
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/{articleId}")
    @Operation(method = "POST", summary = "댓글 생성 API", description = "articleId로 조회한 게시글에 댓글을 생성하는 API")
    public ResponseEntity<CustomResponse<ReplyResponseDTO.ResponsePreviewDto>> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        ReplyResponseDTO.ResponsePreviewDto responseDto = replyCommandService.createReply(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

//    @GetMapping("/{articleId}/replies")
//    @Operation(method = "GET", summary = "게시글 별 댓글 조회 API", description = "articleId로 조회한 게시글의 댓글 전체 조회")
//    public CustomResponse<List<ReplyResponseDTO.ResponsePreviewDto>> getRepliesByArticleId(@PathVariable("articleId") Long articleId) {
//        List<ReplyResponseDTO.ResponsePreviewDto> replies = replyQueryService.getRepliesByArticle(articleId);
//        return CustomResponse.onSuccess(replies);
//    }

    @GetMapping("/{articleId}/replies")
    @Operation(method = "GET", summary = "댓글 offset 페이지네이션 API", description = "댓글 offset 페이지네이션")
    public CustomResponse<ReplyResponseDTO.ResponsePagePreviewDto> getRepiesByArticle(
            @PathVariable("articleId") Long articleId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size
    ){
        ReplyResponseDTO.ResponsePagePreviewDto replies = replyQueryService.getRepliesByArticleId(articleId,page,size);
        return CustomResponse.onSuccess(replies);
    }

    //수정
    @PutMapping("/replies/{replyId}")
    @Operation(method = "PUT", summary = "댓글 수정 API", description = "댓글 수정하는 API")
    public CustomResponse<ReplyResponseDTO.ResponsePreviewDto> updateReply(
            @PathVariable("replyId") Long replyId,
            @RequestBody ReplyRequestDTO.UpdateReplyDTO dto){
        ReplyResponseDTO.ResponsePreviewDto responseDto = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(responseDto);
    }
    //삭제
    @DeleteMapping("/replies/{replyId}")
    @Operation(method = "DELETE", summary = "댓글 삭제 API", description = "댓글 삭제하는 API")
    public CustomResponse<String> deleteArticle(@PathVariable("replyId") Long replyId){
        replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(HttpStatus.NO_CONTENT,"해당 댓글이 삭제되었습니다.");
    }

}

