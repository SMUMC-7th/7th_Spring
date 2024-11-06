package com.example.umc7th.domain.member.service;

import com.example.umc7th.domain.member.dto.MemberLoginRequest;
import com.example.umc7th.domain.member.dto.MemberResponse;
import com.example.umc7th.domain.member.dto.MemberSignUpRequest;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 회원가입 로직
    public MemberResponse signUp(MemberSignUpRequest request) {
        // 이미 등록된 이메일인지 확인
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 비밀번호 인코딩 및 Member 엔티티 생성
        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER") // 기본 역할 설정
                .build();

        memberRepository.save(member);
        return new MemberResponse(member);
    }

    // 로그인 로직
    public String login(MemberLoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.PASSWORD_MISMATCH);
        }

        // 토큰 발급
        return jwtProvider.createAccessToken(member);
    }
}