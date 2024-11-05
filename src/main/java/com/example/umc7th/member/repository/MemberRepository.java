package com.example.umc7th.member.repository;

import com.example.umc7th.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndPassword(String email, String password);

    Member findByEmailAndPassword(String email, String password);

}
