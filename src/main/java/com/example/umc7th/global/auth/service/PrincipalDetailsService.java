package com.example.umc7th.global.auth.service;

import com.example.umc7th.global.auth.domain.PrincipalDetails;
import com.example.umc7th.member.entity.Member;
import com.example.umc7th.member.error.MemberErrorCode;
import com.example.umc7th.member.error.exception.MemberCustomException;
import com.example.umc7th.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    // email을 이용해 사용자를 가져오기 위해 선언
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(() ->
                new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new PrincipalDetails(member);
    }
}