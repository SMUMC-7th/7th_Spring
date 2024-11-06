package com.example.umc7th.domain.member.controller;

import com.example.umc7th.domain.member.converter.MemberConverter;
import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.service.command.MemberCommandService;
import com.example.umc7th.domain.member.service.query.MemberQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/members/signup")
    @Operation(summary = "회원 가입")
    public CustomResponse<MemberResponseDTO.CreateMemberResultDTO> createMember(@RequestBody MemberRequestDTO.CreateMemberDTO dto) {

        return CustomResponse.onSuccess(memberCommandService.createMember(dto));
    }

    @PostMapping("/members/login")
    @Operation(summary = "로그인")
    public CustomResponse<MemberResponseDTO.LoginResponseDTO> login(@RequestBody MemberRequestDTO.LoginRequestDTO dto) {

        return CustomResponse.onSuccess(memberCommandService.login(dto));
    }

    @PutMapping("/members/{memberId}")
    @Operation(summary = "회원정보 수정")
    public CustomResponse<String> updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDTO.UpdateMemberDTO dto) {

        memberCommandService.updateMember(memberId, dto);
        return CustomResponse.onSuccess("회원정보 수정에 성공하였습니다.");
    }

    @DeleteMapping("/members/{memberId}")
    @Operation(summary = "회원정보 삭제(탈퇴)")
    public CustomResponse<String> deleteMember(@PathVariable Long memberId) {

        memberCommandService.deleteMember(memberId);
        return CustomResponse.onSuccess("회원정보 삭제(탈퇴)에 성공하였습니다.");
    }

    @GetMapping("/members/{memberId}")
    @Operation(summary = "회원정보 하나 조회")
    public CustomResponse<MemberResponseDTO.MemberViewDTO> getMember(@PathVariable Long memberId) {

        return CustomResponse.onSuccess(memberQueryService.getMember(memberId));
    }

}
