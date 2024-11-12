package com.example.umc7th.member.service;

import com.example.umc7th.global.jwt.JwtProvider;
import com.example.umc7th.member.dto.LoginResponseDTO;
import com.example.umc7th.member.dto.SignUpDTO;
import com.example.umc7th.member.entity.Member;
import com.example.umc7th.member.error.MemberErrorCode;
import com.example.umc7th.member.error.exception.MemberCustomException;
import com.example.umc7th.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public void signUp(SignUpDTO dto) {
        validateInput(dto);
        Member member = Member
                .builder()
                .email(dto.email())
                .password(encoder.encode(dto.password()))
                .build();
        memberRepository.save(member);
    }

    private void validateInput(SignUpDTO dto) {
        if (dto.email().isEmpty()) {
            throw new MemberCustomException(MemberErrorCode.INVALID_INPUT);
        }
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberCustomException(MemberErrorCode.DUPLICATE_EMAIL);
        }
        if (dto.password().isEmpty()) {
            throw new MemberCustomException(MemberErrorCode.INVALID_INPUT);
        }
    }

    public LoginResponseDTO login(SignUpDTO dto) {
        if (memberRepository.existsByEmailAndPassword(dto.email(), dto.password())) {
            throw new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND);
        }
        Member member = memberRepository.findByEmail(dto.email()).orElseThrow(
                () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND)
        );
        if (!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberCustomException(MemberErrorCode.INVALID_INPUT);
        }
        String accessToken = jwtProvider.createAccessToken(member);
        String refreshToken = jwtProvider.createRefreshToken(member);
        return new LoginResponseDTO(accessToken, refreshToken);
    }
}
