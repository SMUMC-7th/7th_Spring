package com.example.umc7th.domain.member.controller;


import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.service.command.MemberCommandService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    /** 회원 로그인 API */
    @PostMapping("/login")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> login(@RequestBody MemberRequestDTO.MemberLoginDTO dto) {
        return CustomResponse.onSuccess(memberCommandService.login(dto));
    }

    /** 회원가입 API */
    @PostMapping("/signup")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> signup(@RequestBody MemberRequestDTO.MemberSignUpDTO dto) {
        return CustomResponse.onSuccess(memberCommandService.signUp(dto));
    }
}