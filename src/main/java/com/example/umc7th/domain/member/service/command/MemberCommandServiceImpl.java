package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.converter.MemberConverter;
import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Override
    public MemberResponseDTO.CreateMemberResultDTO createMember(MemberRequestDTO.CreateMemberDTO dto) {

        Member member = MemberConverter.toMember(dto, encoder);
        memberRepository.save(member);

        return MemberConverter.toCreateMemberResultDTO(member);
    }

    @Override
    public MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto) {

        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        String accessToken = jwtProvider.createAccessToken(member);
        String refreshToken = jwtProvider.createRefreshToken(member);

        return MemberConverter.toLoginResponseDTO(member, accessToken, refreshToken);

    }

    @Override
    public void updateMember(Long memberId, MemberRequestDTO.UpdateMemberDTO dto) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        member.update(dto.getEmail(), dto.getPassword());
    }

    @Override
    public void deleteMember(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        memberRepository.delete(member);
    }

}
