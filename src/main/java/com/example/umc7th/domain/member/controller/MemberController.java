package com.example.umc7th.domain.member.controller;

import com.example.umc7th.domain.member.dto.request.MemberReqDto;
import com.example.umc7th.domain.member.dto.response.MemberResDto;
import com.example.umc7th.domain.member.service.command.MemberCommandService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "회원 관련 API", description = "회원 관련 API 입니다.")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @Operation(summary = "회원 등록", description = "회원가입을 위한 이메일과 비밀번호 정보를 받아 새 회원을 등록합니다.")
    @PostMapping("/register")
    public ResponseEntity<CustomResponse<MemberResDto.MemberPreviewDto>> createMember(
            @RequestBody MemberReqDto.CreateMemberRequestDto requestDto) {
        MemberResDto.MemberPreviewDto responseDto = memberCommandService.createMember(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

    @Operation(summary = "회원 로그인", description = "이메일과 비밀번호로 회원 로그인을 처리하고, 엑세스 토큰과 리프레시 토큰을 반환합니다.")
    @PostMapping("/login")
    public CustomResponse<MemberResDto.LoginResponseDto> login(
            @RequestBody MemberReqDto.LoginRequestDto requestDto) {
        MemberResDto.LoginResponseDto responseDto = memberCommandService.login(requestDto);
        return CustomResponse.onSuccess(responseDto);
    }
}
