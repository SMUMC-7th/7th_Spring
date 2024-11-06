package com.example.umc7th.domain.member.controller;


import com.example.umc7th.domain.member.dto.MemberLoginRequest;
import com.example.umc7th.domain.member.dto.MemberResponse;
import com.example.umc7th.domain.member.dto.MemberSignUpRequest;
import com.example.umc7th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<MemberResponse> register(@RequestBody MemberSignUpRequest request) {
        MemberResponse response = memberService.signUp(request);
        return ResponseEntity.ok(response);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginRequest request) {
        String token = memberService.login(request);
        return ResponseEntity.ok(token); // 로그인 성공 시 JWT 토큰 반환
    }
}