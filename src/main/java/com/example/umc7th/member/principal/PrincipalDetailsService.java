package com.example.umc7th.member.principal;

import com.example.umc7th.member.entity.Member;
import com.example.umc7th.member.exception.MemberErrorCode;
import com.example.umc7th.member.exception.MemberException;
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
    // email로 PrincipalDetails(UserDetails) 객체를 가져오는 메소드, username은 email로 생각
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(() ->
                new MemberException(MemberErrorCode.NOT_FOUND));
        return new PrincipalDetails(member);
    }
}
