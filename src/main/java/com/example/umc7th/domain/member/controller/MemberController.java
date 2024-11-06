package com.example.umc7th.domain.member.controller;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.service.MemberCommandService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "회원 관련 API")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원 가입", description = "이메일 & 비밀번호 받아 새 회원 가입")
    public CustomResponse<?> createMember(@RequestBody MemberRequestDTO.CreateMemberDTO dto) {

        MemberResponseDTO.MemberPreviewDTO createdMember = memberCommandService.createMember(dto);

        return CustomResponse.onSuccess(createdMember);
    }

    @PostMapping("/login")
    @Operation(summary = "회원 로그인", description = "이메일 & 비밀번호로 로그인 처리하고, access token & refresh token 반환")
    public CustomResponse<?> login(@RequestBody MemberRequestDTO.LoginDTO dto) {

        MemberResponseDTO.LoginMemberResponseDTO loginMemberResponseDTO = memberCommandService.login(dto);
        return CustomResponse.onSuccess(loginMemberResponseDTO);
    }
}
