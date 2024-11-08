package com.example.umc7th.member.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.member.dto.MemberRequestDTO;
import com.example.umc7th.member.dto.MemberResponseDTO;
import com.example.umc7th.member.service.command.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/login")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> login(@RequestBody MemberRequestDTO.MemberLoginDTO dto) {
//        System.out.println("**********************************");
        return CustomResponse.onSuccess(memberCommandService.login(dto));
    }

    @PostMapping("/signup")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> signup(@RequestBody MemberRequestDTO.MemberSignUpDTO dto) {
        return CustomResponse.onSuccess(memberCommandService.signUp(dto));
    }
}
