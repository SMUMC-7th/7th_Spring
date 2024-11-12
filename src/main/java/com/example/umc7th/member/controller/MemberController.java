package com.example.umc7th.member.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc7th.member.dto.SignUpDTO;
import com.example.umc7th.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "이메일과 비밀번호를 입력해서 회원가입하는 API ")
    public CustomResponse<?> signUp(@RequestBody SignUpDTO signUpDTO) {
        memberService.signUp(signUpDTO);
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호를 입력해서 로그인한다.")
    public CustomResponse<?> login(@RequestBody SignUpDTO signUpDTO) {
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, memberService.login(signUpDTO));
    }
}
