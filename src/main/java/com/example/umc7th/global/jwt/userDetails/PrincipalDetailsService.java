package com.example.umc7th.global.jwt.userDetails;

import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 이메일을 통해 사용자 정보를 로드하는 메서드
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // users 테이블에서 이메일로 사용자 정보 조회
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 존재하지 않습니다."));

        // CustomUserDetails로 변환하여 반환
        return new PrincipalDetails(member);
    }

}
