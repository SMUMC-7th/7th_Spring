package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.converter.MemberConverter;
import com.example.umc7th.domain.member.dto.MemberDto;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberConverter memberConverter;

    @Override
    public Member signUp(MemberDto memberDto) {
        // 비밀번호를 인코딩하여 저장
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        Member member = memberConverter.toEntity(memberDto, encodedPassword);
        return memberRepository.save(member);
    }
}
