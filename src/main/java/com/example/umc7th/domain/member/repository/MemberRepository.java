package com.example.umc7th.domain.member.repository;

import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndSocialType(String email, SocialType socialType);
    boolean existsByEmail(String email);
}
