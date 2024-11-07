package com.example.umc7th.service.command;

import com.example.umc7th.converter.MemberConverter;
import com.example.umc7th.dto.request.MemberRequestDto;
import com.example.umc7th.dto.response.MemberResponseDto;
import com.example.umc7th.entity.Member;
import com.example.umc7th.global.apipayload.code.MemberErrorCode;
import com.example.umc7th.global.apipayload.exception.MemberException;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.example.umc7th.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public MemberResponseDto.MemberPreviewDto createMember(MemberRequestDto.CreateMemberRequestDto dto) {
        Member member = MemberConverter.toEntity(dto, passwordEncoder);
        memberRepository.save(member);

        return MemberConverter.from(member);
    }

    @Override
    public MemberResponseDto.MemberPreviewDto updateMember(MemberRequestDto.UpdateMemberRequestDto dto) {
        Member member = memberRepository.findById(dto.getId()).orElseThrow(
                () -> new MemberException(MemberErrorCode.NOT_FOUND));
        String newPassword = passwordEncoder.encode(dto.getPassword());

        member.update(dto.getEmail(), newPassword);

        return MemberConverter.from(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public MemberResponseDto.LoginResponseDto login(MemberRequestDto.LoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                ()-> new MemberException(MemberErrorCode.NOT_FOUND));
        String accessToken = jwtProvider.createAccessToken(member);
        String refreshToken = jwtProvider.createRefreshToken(member);

        return MemberConverter.from(member, accessToken, refreshToken);
    }


}
