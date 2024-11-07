package com.example.umc7th.domain.member.controller;

import com.example.umc7th.domain.member.converter.MemberConverter;
import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.service.command.MemberCommandService;
import com.example.umc7th.domain.member.service.query.MemberQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "멤버 API")
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/signUp")
    @Operation(summary = "회원가입 API")
    public CustomResponse<MemberResponseDTO.MemberInfoDTO> signUp(@RequestBody MemberRequestDTO.SignUpDTO dto) {
        Member createdMember = memberCommandService.signUp(dto);
        return CustomResponse.onSuccess(MemberConverter.toMemberInfoDTO(createdMember));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> signUp(@RequestBody MemberRequestDTO.LoginDTO dto) {
        return CustomResponse.onSuccess(memberQueryService.login(dto));
    }
}
