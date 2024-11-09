package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** 회원의 로그인 및 회원가입 처리와 JWT 토큰 발급을 담당 */
@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    /** 사용자가 입력한 이메일과 비밀번호를 검증하여 로그인 처리 */
    @Override
    public MemberResponseDTO.MemberTokenDTO login(MemberRequestDTO.MemberLoginDTO dto) {

        // 회원 정보를 이메일로 조회, 존재하지 않으면 예외 발생
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(() ->
                new MemberException(MemberErrorCode.NOT_FOUND));

        // 입력한 비밀번호와 저장된 비밀번호가 일치하는지 확인, 일치하지 않으면 예외 발생
        if (!encoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INCORRECT_PASSWORD);
        }

        // 액세스 토큰과 리프레시 토큰을 생성하여 반환
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }

    /** 새로운 사용자를 등록하고, 회원가입 성공 시 토큰을 생성하여 반환 */
    @Override
    public MemberResponseDTO.MemberTokenDTO signUp(MemberRequestDTO.MemberSignUpDTO dto) {

        // 이메일이 이미 존재하면 예외 발생
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new MemberException(MemberErrorCode.ALREADY_EXIST);
        }

        // 새로운 회원 정보 저장, 비밀번호는 암호화하여 저장
        Member member = memberRepository.save(Member.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .role("ROLE_USER")
                .build());

        // 회원가입 성공 시 액세스 토큰과 리프레시 토큰을 생성하여 반환
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }
}