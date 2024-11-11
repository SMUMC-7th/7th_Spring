package com.example.umc7th.domain.member.controller;

import com.example.umc7th.domain.member.dto.MemberDto;
import com.example.umc7th.domain.member.service.command.MemberCommandService;
import com.example.umc7th.domain.member.service.query.MemberQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    public MemberController(MemberCommandService memberCommandService, MemberQueryService memberQueryService) {
        this.memberCommandService = memberCommandService;
        this.memberQueryService = memberQueryService;
    }

    // 회원가입
    @PostMapping("/signup")
    public CustomResponse<String> signUp(@RequestBody MemberDto memberDto) {
        // 회원가입 처리
        memberCommandService.signUp(memberDto);

        // 성공적인 응답 반환
        return CustomResponse.onSuccess("회원가입이 성공적으로 완료되었습니다.");
    }

    // 로그인
    @PostMapping("/login")
    public CustomResponse<String> login(@RequestBody MemberDto memberDto) {
        boolean isLoginSuccess = memberQueryService.login(memberDto);

        if (isLoginSuccess) {
            // 로그인 성공
            return CustomResponse.onSuccess("로그인 성공");
        } else {
            // 로그인 실패
            return CustomResponse.onFailure(false, HttpStatus.UNAUTHORIZED, "LOGIN_FAILED", "로그인에 실패했습니다.", null);
        }
    }
}
