package com.example.umc7th.domain.member.service.query;

import com.example.umc7th.domain.member.dto.MemberDto;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean login(MemberDto memberDto) {
        Member member = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 비밀번호가 일치하는지 확인
        return passwordEncoder.matches(memberDto.getPassword(), member.getPassword());
    }
}
